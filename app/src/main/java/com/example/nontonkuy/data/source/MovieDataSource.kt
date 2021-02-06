package com.example.nontonkuy.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nontonkuy.data.source.remote.response.ResponseDetailMovie
import com.example.nontonkuy.data.source.remote.response.ResponseListMovie
import com.example.nontonkuy.data.source.remote.response.ResultsItemListMovie

interface MovieDataSource {
    fun getMovies(): MutableLiveData<ArrayList<ResultsItemListMovie>>
    fun getDetailMovie(idMovie: String?) : MutableLiveData<ResponseDetailMovie>
    fun getRecomendation(idMovie: String?) : MutableLiveData<ArrayList<ResultsItemListMovie>>
}