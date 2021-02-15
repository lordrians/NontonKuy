package com.example.nontonkuy.di

import android.content.Context
import com.example.nontonkuy.data.source.local.MovieLocalDataSource
import com.example.nontonkuy.data.source.local.entity.room.NontonKuyDatabase
import com.example.nontonkuy.data.source.repository.MovieRepository
import com.example.nontonkuy.data.source.remote.movie.MovieRemoteDataSource
import com.example.nontonkuy.utils.AppExecutors

object MovieInjection {
    fun provideRepository(mContext: Context): MovieRepository {

        val database = NontonKuyDatabase.getInstance(mContext)

        val remoteDataSource = MovieRemoteDataSource.getInstance(mContext)
        val localDataSource = MovieLocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()
        return MovieRepository.getInstance(remoteDataSource,localDataSource,appExecutors)
    }
}