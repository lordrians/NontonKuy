package com.example.nontonkuy.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.nontonkuy.data.source.local.entity.TvShowEntity
import com.example.nontonkuy.data.source.local.entity.room.TvShowDao

class TvShowLocalDataSource(
        private val mTvShowDao: TvShowDao
) {

    companion object {
        private var INSTANCE: TvShowLocalDataSource? = null
        fun getInstance(
            tvShowDao: TvShowDao
        ): TvShowLocalDataSource = INSTANCE
                ?: TvShowLocalDataSource(tvShowDao)
    }

    fun getListTvShow(): DataSource.Factory<Int, TvShowEntity> = mTvShowDao.getListTvShow()

    fun getDetailTvShow(idTvShow: String?): LiveData<TvShowEntity> = mTvShowDao.getDetailTvShow(idTvShow)

    fun getListFavTvShow(): DataSource.Factory<Int, TvShowEntity> = mTvShowDao.getListFavTvShow()

    fun insertTvShows(TvShows: List<TvShowEntity>) = mTvShowDao.insertTvShows(TvShows)

    fun setFavTvShow(TvShow: TvShowEntity, newState: Boolean){
        TvShow.isFav = newState
        mTvShowDao.updateTvShow(TvShow)
    }

    fun updateTvShow(TvShow: TvShowEntity, newState: Boolean) {
        TvShow.isFav = newState
        mTvShowDao.updateTvShow(TvShow)
    }

}