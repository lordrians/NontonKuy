package com.example.nontonkuy.data.source.local.entity.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.nontonkuy.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_entities")
    fun getListMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie_entities WHERE id = :id")
    fun getDetailMovie(id: String?): LiveData<MovieEntity>

    @Query("SELECT * FROM movie_entities WHERE isFav = 1")
    fun getListFavMovie(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)
}