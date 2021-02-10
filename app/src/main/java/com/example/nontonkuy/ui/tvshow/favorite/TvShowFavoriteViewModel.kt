package com.example.nontonkuy.ui.tvshow.favorite

import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.source.repository.TvShowRepository

class TvShowFavoriteViewModel(
    private val repository: TvShowRepository
): ViewModel(){
    fun getFavoriteTvShow() = repository.getFavTvShow()
}