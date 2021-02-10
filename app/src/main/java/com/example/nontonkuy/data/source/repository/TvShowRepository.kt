package com.example.nontonkuy.data.source.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.nontonkuy.data.source.NetworkBoundResource
import com.example.nontonkuy.data.source.TvShowDataSource
import com.example.nontonkuy.data.source.local.entity.TvShowEntity
import com.example.nontonkuy.data.source.local.TvShowLocalDataSource
import com.example.nontonkuy.utils.ApiResponse
import com.example.nontonkuy.data.source.remote.tvshow.TvShowRemoteDataSource
import com.example.nontonkuy.data.source.remote.response.ResponseDetailTvShow
import com.example.nontonkuy.data.source.remote.response.ResultsItemListTvShow
import com.example.nontonkuy.utils.AppExecutors
import com.example.nontonkuy.utils.Resource

class TvShowRepository private constructor(
        private val remoteDataSource: TvShowRemoteDataSource,
        private val tvShowLocalDataSource: TvShowLocalDataSource,
        private val appExecutors: AppExecutors
): TvShowDataSource {

        companion object {
                @Volatile
                private var instance: TvShowRepository? = null

                fun getInstance(
                        remoteData: TvShowRemoteDataSource,
                        tvShowLocalDataSource: TvShowLocalDataSource,
                        appExecutors: AppExecutors
                ): TvShowRepository =
                        instance
                                ?: synchronized(this) {
                                instance
                                        ?: TvShowRepository(remoteData, tvShowLocalDataSource, appExecutors)
                        }
        }


        override fun getRecomendation(idTvShow: String?): MutableLiveData<ArrayList<ResultsItemListTvShow>> {
                val recomendationTvShowResult = MutableLiveData<ArrayList<ResultsItemListTvShow>>()

                remoteDataSource.getRecomendation(idTvShow, object : TvShowRemoteDataSource.LoadRecomendationTvShowCallback{
                        override fun onRecomendationiLoaded(recomendationTvShow: ArrayList<ResultsItemListTvShow>?) {
                                if (recomendationTvShow != null){
                                        recomendationTvShowResult.value = recomendationTvShow
                                }
                        }
                })
                return recomendationTvShowResult
        }

        override fun getListTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
                return object : NetworkBoundResource<PagedList<TvShowEntity>, List<ResultsItemListTvShow>>(appExecutors){
                        override fun loadFromDb(): LiveData<PagedList<TvShowEntity>> {
                                val config = PagedList.Config.Builder()
                                        .setEnablePlaceholders(false)
                                        .setInitialLoadSizeHint(4)
                                        .setPageSize(4)
                                        .build()
                                return LivePagedListBuilder(tvShowLocalDataSource.getListTvShow(), config).build()
                        }

                        override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                                data == null || data.isEmpty()

                        override fun createCall(): LiveData<ApiResponse<List<ResultsItemListTvShow>>> =
                                remoteDataSource.getListTvShows()

                        override fun saveCallResult(data: List<ResultsItemListTvShow>) {
                                val tvShowList = ArrayList<TvShowEntity>()
                                for (response in data){
                                        val tvShow = TvShowEntity(
                                                id = response.id.toString(),
                                                originalName = response.originalName,
                                                voteAverage = response.voteAverage,
                                                firstAirDate = response.firstAirDate,
                                                episodeRunTime = 0,
                                                genres = "",
                                                posterPath = response.posterPath,
                                                isFav = false
                                        )
                                        tvShowList.add(tvShow)
                                }
                                tvShowLocalDataSource.insertTvShows(tvShowList)
                        }
                }.asLiveData()
        }

        override fun getDetailTvShows(idTvShow: String?): LiveData<Resource<TvShowEntity>> {
                return object : NetworkBoundResource<TvShowEntity, ResponseDetailTvShow>(appExecutors){
                        override fun loadFromDb(): LiveData<TvShowEntity> =
                                tvShowLocalDataSource.getDetailTvShow(idTvShow)

                        override fun shouldFetch(data: TvShowEntity?): Boolean =
                                data != null && data.genres == ""

                        override fun createCall(): LiveData<ApiResponse<ResponseDetailTvShow>> =
                                remoteDataSource.getDetailTvShows(idTvShow.toString())

                        override fun saveCallResult(data: ResponseDetailTvShow) {
                                var genresAdded = ""
                                for (i in data.genres?.indices!!){
                                        if (i < data.genres.size - 1)
                                                genresAdded += data.genres[i]?.name + "; "
                                        else
                                                genresAdded += data.genres[i]?.name
                                }

                                val tvShow = TvShowEntity(
                                        id = data.id.toString(),
                                        backdropPath = data.backdropPath,
                                        posterPath = data.posterPath,
                                        originalName = data.originalName,
                                        genres = genresAdded,
                                        voteAverage = data.voteAverage,
                                        firstAirDate = data.firstAirDate,
                                        episodeRunTime = data.episodeRunTime?.get(0),
                                        numberOfSeasons = data.numberOfSeasons,
                                        numberOfEpisodes = data.numberOfEpisodes,
                                        status = data.status,
                                        overview = data.overview
                                )
                                tvShowLocalDataSource.updateTvShow(tvShow, false)
                        }
                }.asLiveData()
        }

        override fun setFavTvShow(tvShow: TvShowEntity, state: Boolean) {
                appExecutors.diskIO().execute {
                        tvShowLocalDataSource.setFavTvShow(tvShow, state)
                }
        }

        override fun getFavTvShow(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(tvShowLocalDataSource.getListFavTvShow(), config).build()
        }


}