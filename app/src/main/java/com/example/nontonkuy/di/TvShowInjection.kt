package com.example.nontonkuy.di

import android.content.Context
import com.example.nontonkuy.data.source.repository.TvShowRepository
import com.example.nontonkuy.data.source.remote.tvshow.TvShowRemoteDataSource

object TvShowInjection {
    fun provideRespository(mContext: Context): TvShowRepository {
        val remoteDataSource = TvShowRemoteDataSource.getInstance()
        return TvShowRepository.getInstance(remoteDataSource)
    }
}