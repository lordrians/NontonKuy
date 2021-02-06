package com.example.nontonkuy.data.source

import androidx.lifecycle.MutableLiveData
import com.example.nontonkuy.data.source.remote.TvShowRemoteDataSource
import com.example.nontonkuy.data.source.remote.response.ResponseDetailTvShow
import com.example.nontonkuy.data.source.remote.response.ResultsItemListTvShow

class TvShowRepository private constructor(
        private val remoteDataSource: TvShowRemoteDataSource
): TvShowDataSource {

        companion object {
                @Volatile
                private var instance: TvShowRepository? = null

                fun getInstance(remoteData: TvShowRemoteDataSource): TvShowRepository =
                        instance ?: synchronized(this) {
                                instance ?: TvShowRepository(remoteData)
                        }
        }



        override fun getTvShows(): MutableLiveData<ArrayList<ResultsItemListTvShow>> {
                val tvShowResults = MutableLiveData<ArrayList<ResultsItemListTvShow>>()

                remoteDataSource.getTvShows(object : TvShowRemoteDataSource.LoadTvShowsCallback{
                        override fun onTvShowsLoaded(listTvShow: ArrayList<ResultsItemListTvShow>?) {
                                if (listTvShow != null){
                                        tvShowResults.value = listTvShow
                                }
                        }
                })
                return tvShowResults
        }

        override fun getDetailTvShow(idTvShow: String?): MutableLiveData<ResponseDetailTvShow> {
                val detailTvShow = MutableLiveData<ResponseDetailTvShow>()

                remoteDataSource.getDetailTvShow(idTvShow, object : TvShowRemoteDataSource.LoadDetailTvShowCallback{
                        override fun onDetailTvShowLoaded(movie: ResponseDetailTvShow?) {
                                if (movie != null){
                                        detailTvShow.value = movie
                                }
                        }
                })
                return detailTvShow
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


}