package com.example.nontonkuy.data.source.local.entity.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.nontonkuy.data.source.local.entity.TvShowEntity

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tvshow_entities")
    fun getListTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshow_entities WHERE id = :id")
    fun getDetailTvShow(id: String?): LiveData<TvShowEntity>

    @Query("SELECT * FROM tvshow_entities WHERE isFav = 1")
    fun getListFavTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvshows: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvshow: TvShowEntity)
}