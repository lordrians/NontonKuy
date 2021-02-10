package com.example.nontonkuy.ui.movie.favorite

import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.source.repository.MovieRepository

class MovieFavoriteViewModel (
    private val repository: MovieRepository
): ViewModel(){

    fun getFavoriteMovie() = repository.getFavMovie()

}