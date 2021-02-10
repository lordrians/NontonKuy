package com.example.nontonkuy.ui.movie.detail

import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.source.repository.MovieRepository

class MovieDetailViewModel(
        private val movieRepository: MovieRepository
): ViewModel() {

    fun getDetailMovie(idMovie: String?) = movieRepository.getDetailMovie(idMovie)
    fun getRecomendationMovie(idMovie: String?) = movieRepository.getRecomendation(idMovie)


    fun getDetailMovies(idMovie: String?) = movieRepository.getDetailMovies(idMovie)

}