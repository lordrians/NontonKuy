package com.example.nontonkuy.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.nontonkuy.data.source.local.entity.TvShowEntity
import com.example.nontonkuy.data.source.remote.response.ResponseDetailTvShow
import com.example.nontonkuy.data.source.remote.response.ResultsItemListTvShow
import com.example.nontonkuy.utils.Resource

interface TvShowDataSource {

    fun getRecomendation(idTvShow: String?) : MutableLiveData<ArrayList<ResultsItemListTvShow>>

    fun getListTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getDetailTvShows(idTvShow: String?): LiveData<Resource<TvShowEntity>>

    fun setFavTvShow(TvShow: TvShowEntity, state: Boolean)
    fun getFavTvShow(): LiveData<PagedList<TvShowEntity>>

}