package com.example.nontonkuy.data.source

import androidx.lifecycle.MutableLiveData
import com.example.nontonkuy.data.source.remote.response.ResponseDetailTvShow
import com.example.nontonkuy.data.source.remote.response.ResultsItemListTvShow

interface TvShowDataSource {

    fun getTvShows() : MutableLiveData<ArrayList<ResultsItemListTvShow>>
    fun getDetailTvShow(idTvShow: String?) : MutableLiveData<ResponseDetailTvShow>
    fun getRecomendation(idTvShow: String?) : MutableLiveData<ArrayList<ResultsItemListTvShow>>

}