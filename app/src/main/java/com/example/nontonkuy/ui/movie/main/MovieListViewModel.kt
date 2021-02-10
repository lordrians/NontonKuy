package com.example.nontonkuy.ui.movie.main

import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.source.repository.MovieRepository

class MovieListViewModel (
        private val movieRepository: MovieRepository
) : ViewModel() {

    fun getMovies() = movieRepository.getListMovie()

}