package com.example.nontonkuy.ui.tvshow.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.source.local.entity.TvShowEntity
import com.example.nontonkuy.data.source.repository.TvShowRepository
import com.example.nontonkuy.utils.Resource

class TvShowDetailViewModel(
        private val tvShowRepository: TvShowRepository
): ViewModel() {

    private lateinit var detailTvShow: LiveData<Resource<TvShowEntity>>

    fun getDetailTvShow(idTvShow: String?): LiveData<Resource<TvShowEntity>>{
        detailTvShow = tvShowRepository.getDetailTvShows(idTvShow)
        return detailTvShow
    }
    fun getRecomendationTvShow(idTvShow: String?) = tvShowRepository.getRecomendation(idTvShow)

    fun setFavoriteTvShow(){
        val newData = detailTvShow.value
        if (newData?.data != null){
            val newState = !newData.data.isFav
            tvShowRepository.setFavTvShow(newData.data, newState)
        }
    }

}