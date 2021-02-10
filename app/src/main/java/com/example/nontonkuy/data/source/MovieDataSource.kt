package com.example.nontonkuy.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.data.source.remote.response.ResponseDetailMovie
import com.example.nontonkuy.data.source.remote.response.ResponseListMovie
import com.example.nontonkuy.data.source.remote.response.ResultsItemListMovie
import com.example.nontonkuy.utils.Resource

interface MovieDataSource {
    fun getMovies(): MutableLiveData<ArrayList<ResultsItemListMovie>>
    fun getDetailMovie(idMovie: String?) : MutableLiveData<ResponseDetailMovie>
    fun getRecomendation(idMovie: String?) : MutableLiveData<ArrayList<ResultsItemListMovie>>

    fun getListMovie(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getDetailMovies(idMovie: String?): LiveData<Resource<MovieEntity>>

    fun setFavMovie(movie: MovieEntity, state: Boolean)
    fun getFavMovie(): LiveData<PagedList<MovieEntity>>
}