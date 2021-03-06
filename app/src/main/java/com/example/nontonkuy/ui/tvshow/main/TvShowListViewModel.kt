package com.example.nontonkuy.ui.tvshow.main

import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.source.repository.TvShowRepository

class TvShowListViewModel(
        private val tvShowRepository: TvShowRepository
): ViewModel() {

    fun getTvShows() = tvShowRepository.getListTvShow()


}