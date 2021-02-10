package com.example.nontonkuy.ui.tvshow.detail

import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.source.repository.TvShowRepository

class TvShowDetailViewModel(
        private val tvShowRepository: TvShowRepository
): ViewModel() {

    fun getDetailTvShow(idTvShow: String?) = tvShowRepository.getDetailTvShows(idTvShow)
    fun getRecomendationTvShow(idTvShow: String?) = tvShowRepository.getRecomendation(idTvShow)

}