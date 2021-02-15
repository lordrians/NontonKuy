package com.example.nontonkuy.data.source.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.nontonkuy.data.source.MovieDataSource
import com.example.nontonkuy.data.source.NetworkBoundResource
import com.example.nontonkuy.data.source.local.MovieLocalDataSource
import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.data.source.remote.movie.MovieRemoteDataSource
import com.example.nontonkuy.data.source.remote.response.ResponseDetailMovie
import com.example.nontonkuy.data.source.remote.response.ResultsItemListMovie
import com.example.nontonkuy.utils.ApiResponse
import com.example.nontonkuy.utils.AppExecutors
import com.example.nontonkuy.utils.Resource

class FakeMovieRepository constructor(
        private val remoteDataSource: MovieRemoteDataSource,
        private val localDataSource: MovieLocalDataSource,
        private val appExecutors: AppExecutors
) : MovieDataSource {
    override fun getRecomendation(idMovie: String?): MutableLiveData<ArrayList<ResultsItemListMovie>> {
        TODO("Not yet implemented")
    }

    override fun getListMovie(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<ResultsItemListMovie>>(appExecutors){
            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getListMovie(),config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsItemListMovie>>> =
                remoteDataSource.getListMovie()

            override fun saveCallResult(data: List<ResultsItemListMovie>) {
                val listMovie = ArrayList<MovieEntity>()
                for (response in data){
                    val movie = MovieEntity(
                        id = response.id,
                        originalTitle = response.originalTitle,
                        voteAverage = response.voteAverage,
                        releaseDate = response.releaseDate,
                        runtime = 0,
                        genres = "",
                        posterPath = response.posterPath,
                        isFav = false
                    )
                    listMovie.add(movie)
                }
                localDataSource.insertMovies(listMovie)
            }
        }.asLiveData()
    }

    override fun getDetailMovies(idMovie: String?): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, ResponseDetailMovie>(appExecutors){
            override fun loadFromDb(): LiveData<MovieEntity> = localDataSource.getDetailMovie(idMovie)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data != null &&  data.genres == ""

            override fun createCall(): LiveData<ApiResponse<ResponseDetailMovie>> =
            remoteDataSource.getDetailMovies(idMovie.toString())

            override fun saveCallResult(data: ResponseDetailMovie) {
                var genresAdded = ""

                for (i in data.genres?.indices!!){
                    if (i < data.genres!!.size - 1)
                        genresAdded += data.genres!![i]?.name + "; "
                    else
                        genresAdded += data.genres!![i]?.name
                }

                val movie = MovieEntity(
                    id = data.id,
                    backdropPath = data.backdropPath,
                    posterPath = data.posterPath,
                    originalTitle = data.originalTitle,
                    genres =  genresAdded,
                    voteAverage = data.voteAverage?.toDouble(),
                    releaseDate = data.releaseDate,
                    runtime = data.runtime,
                    budget = data.budget,
                    revenue = data.revenue,
                    overview = data.overview
                )
                localDataSource.updateMovie(movie, false)
            }
        }.asLiveData()
    }

    override fun setFavMovie(movie: MovieEntity, state: Boolean) {
        localDataSource.setFavMovie(movie, state)
    }

    override fun getFavMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getListFavMovie(), config).build()
    }
}