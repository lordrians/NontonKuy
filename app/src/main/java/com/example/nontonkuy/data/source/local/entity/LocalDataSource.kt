package com.example.nontonkuy.data.source.local.entity

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.nontonkuy.data.source.local.entity.room.MovieDao

class LocalDataSource(
    private val mMovieDao: MovieDao
) {

    companion object {
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(
            movieDao: MovieDao
        ): LocalDataSource = INSTANCE ?: LocalDataSource(movieDao)
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