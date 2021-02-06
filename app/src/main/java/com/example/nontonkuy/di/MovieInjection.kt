package com.example.nontonkuy.di

import android.content.Context
import com.example.nontonkuy.data.source.MovieRepository
import com.example.nontonkuy.data.source.remote.MovieRemoteDataSource

object MovieInjection {
    fun provideRepository(mContext: Context): MovieRepository{
        val remoteDataSource = MovieRemoteDataSource.getInstance()
        return MovieRepository.getInstance(remoteDataSource)
    }
}