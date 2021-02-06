package com.example.nontonkuy.data.source

import androidx.lifecycle.MutableLiveData
import com.example.nontonkuy.data.source.remote.MovieRemoteDataSource
import com.example.nontonkuy.data.source.remote.response.ResponseDetailMovie
import com.example.nontonkuy.data.source.remote.response.ResultsItemListMovie

class MovieRepository private constructor(
        private val remoteDataSource: MovieRemoteDataSource
): MovieDataSource{

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance (remoteData: MovieRemoteDataSource): MovieRepository =
                instance ?: synchronized(this){
                    instance ?: MovieRepository(remoteData)
                }
    }

    override fun getMovies(): MutableLiveData<ArrayList<ResultsItemListMovie>> {
        val movieResults = MutableLiveData<ArrayList<ResultsItemListMovie>>()

        remoteDataSource.getMovies(object : MovieRemoteDataSource.LoadMoviesCallback{
            override fun onMoviesLoaded(movies: ArrayList<ResultsItemListMovie>?) {
                if (movies != null){
                    movieResults.value = movies
                }
            }
        })
        return movieResults
    }

    override fun getDetailMovie(idMovie: String?): MutableLiveData<ResponseDetailMovie> {
        val detailMovieResult = MutableLiveData<ResponseDetailMovie>()

        remoteDataSource.getDetailMovie(idMovie, object : MovieRemoteDataSource.LoadDetailMovieCallback{
            override fun onDetailMovieLoaded(movie: ResponseDetailMovie?) {
                if (movie != null){
                    detailMovieResult.value = movie
                }

            }
        })
        return detailMovieResult
    }

    override fun getRecomendation(idMovie: String?): MutableLiveData<ArrayList<ResultsItemListMovie>> {
        val recomendationMovieResult = MutableLiveData<ArrayList<ResultsItemListMovie>>()

        remoteDataSource.getRecomendationMovie(idMovie, object : MovieRemoteDataSource.LoadRecomendationMovieCallback{
            override fun onRecomendationLoaded(recomendationMovie: ArrayList<ResultsItemListMovie>?) {
                if (recomendationMovie != null){
                    recomendationMovieResult.value = recomendationMovie
                }
            }
        })
        return  recomendationMovieResult

    }

}