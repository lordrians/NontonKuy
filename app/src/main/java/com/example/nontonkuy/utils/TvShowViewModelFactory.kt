package com.example.nontonkuy.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nontonkuy.data.source.repository.TvShowRepository
import com.example.nontonkuy.di.TvShowInjection
import com.example.nontonkuy.ui.tvshow.TvShowListViewModel
import com.example.nontonkuy.ui.tvshow.detail.TvShowDetailViewModel

class TvShowViewModelFactory private constructor(
        private val tvShowRepository: TvShowRepository
): ViewModelProvider.NewInstanceFactory(){

    companion object {
        @Volatile
        private var instance: TvShowViewModelFactory? = null

        fun getInstance(mContext: Context): TvShowViewModelFactory =
                instance ?: synchronized(this){
                    instance ?: TvShowViewModelFactory(TvShowInjection.provideRespository(mContext))
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TvShowListViewModel::class.java) -> {
                TvShowListViewModel(tvShowRepository) as T
            }
            modelClass.isAssignableFrom(TvShowDetailViewModel::class.java) -> {
                TvShowDetailViewModel(tvShowRepository) as T
            }
            else -> throw Throwable("Uknwon ViewModel Class: ${modelClass.name}")
        }
    }
}