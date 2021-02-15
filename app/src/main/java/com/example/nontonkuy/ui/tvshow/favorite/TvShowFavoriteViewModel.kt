package com.example.nontonkuy.ui.tvshow.favorite

import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.source.local.entity.TvShowEntity
import com.example.nontonkuy.data.source.repository.TvShowRepository

class TvShowFavoriteViewModel(
    private val repository: TvShowRepository
): ViewModel(){
    fun getFavoriteTvShow() = repository.getFavTvShow()

    fun setFavTvShow(tvShowEntity: TvShowEntity){
        val newState = !tvShowEntity.isFav
        repository.setFavTvShow(tvShowEntity, newState)
    }
}