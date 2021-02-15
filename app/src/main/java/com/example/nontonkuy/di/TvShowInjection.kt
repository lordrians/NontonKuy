package com.example.nontonkuy.di

import android.content.Context
import com.example.nontonkuy.data.source.local.TvShowLocalDataSource
import com.example.nontonkuy.data.source.local.entity.room.NontonKuyDatabase
import com.example.nontonkuy.data.source.repository.TvShowRepository
import com.example.nontonkuy.data.source.remote.tvshow.TvShowRemoteDataSource
import com.example.nontonkuy.utils.AppExecutors

object TvShowInjection {
    fun provideRespository(mContext: Context): TvShowRepository {

        val database = NontonKuyDatabase.getInstance(mContext)


        val remoteDataSource = TvShowRemoteDataSource.getInstance(mContext)
        val localDataSource = TvShowLocalDataSource.getInstance(database.tvShowDao())
        val appExecutors = AppExecutors()
        return TvShowRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}