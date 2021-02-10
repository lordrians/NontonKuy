package com.example.nontonkuy.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.data.source.repository.MovieRepository
import com.example.nontonkuy.utils.Resource

class MovieDetailViewModel(
        private val movieRepository: MovieRepository
): ViewModel() {

    private lateinit var detailMovie: LiveData<Resource<MovieEntity>>

//    fun getDetailMovies(idMovie: String?) = movieRepository.getDetailMovies(idMovie)
    fun getRecomendationMovie(idMovie: String?) = movieRepository.getRecomendation(idMovie)


    fun getDetailMovies(idMovie: String?): LiveData<Resource<MovieEntity>> {
        detailMovie = movieRepository.getDetailMovies(idMovie)
        return detailMovie
    }

    fun setFavoriteMovie(){
        val newData = detailMovie.value
        if (newData?.data != null){
            val newState = !newData.data.isFav
            movieRepository.setFavMovie(newData.data, newState)
        }
    }

}