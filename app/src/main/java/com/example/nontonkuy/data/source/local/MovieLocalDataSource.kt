package com.example.nontonkuy.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.data.source.local.entity.room.MovieDao

class MovieLocalDataSource(
    private val mMovieDao: MovieDao
) {

    companion object {
        private var INSTANCE: MovieLocalDataSource? = null
        fun getInstance(
            movieDao: MovieDao
        ): MovieLocalDataSource = INSTANCE
                ?: MovieLocalDataSource(movieDao)
    }

    fun getListMovie(): DataSource.Factory<Int, MovieEntity> = mMovieDao.getListMovie()

    fun getDetailMovie(idMovie: String?): LiveData<MovieEntity> = mMovieDao.getDetailMovie(idMovie)

    fun getListFavMovie(): DataSource.Factory<Int, MovieEntity> = mMovieDao.getListFavMovie()

    fun insertMovies(movies: List<MovieEntity>) = mMovieDao.insertMovies(movies)

    fun setFavMovie(movie: MovieEntity, newState: Boolean){
        movie.isFav = newState
        mMovieDao.updateMovie(movie)
    }

    fun updateMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFav = newState
        mMovieDao.updateMovie(movie)
    }


}