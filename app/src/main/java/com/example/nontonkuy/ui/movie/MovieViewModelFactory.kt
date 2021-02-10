package com.example.nontonkuy.ui.movie

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nontonkuy.data.source.repository.MovieRepository
import com.example.nontonkuy.di.MovieInjection
import com.example.nontonkuy.ui.movie.detail.MovieDetailViewModel
import com.example.nontonkuy.ui.movie.favorite.MovieFavoriteViewModel
import com.example.nontonkuy.ui.movie.main.MovieListViewModel

class MovieViewModelFactory private constructor(
        private val movieRepository: MovieRepository
): ViewModelProvider.NewInstanceFactory(){

    companion object {
        @Volatile
        private var instance: MovieViewModelFactory? = null

        fun getInstance(mContext: Context): MovieViewModelFactory =
                instance
                        ?: synchronized(this){
                    instance
                            ?: MovieViewModelFactory(MovieInjection.provideRepository(mContext))
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieListViewModel::class.java) -> {
                MovieListViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> {
                MovieDetailViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(MovieFavoriteViewModel::class.java) -> {
                MovieFavoriteViewModel(movieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}