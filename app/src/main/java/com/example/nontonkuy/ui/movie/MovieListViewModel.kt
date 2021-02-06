package com.example.nontonkuy.ui.movie

import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.source.MovieRepository

class MovieListViewModel (
        private val movieRepository: MovieRepository
) : ViewModel() {

    fun getMovies() = movieRepository.getMovies()

}