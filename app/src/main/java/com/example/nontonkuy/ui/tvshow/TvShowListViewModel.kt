package com.example.nontonkuy.ui.tvshow

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.source.TvShowRepository
import com.example.nontonkuy.data.source.remote.response.ResponseListTvShow
import com.example.nontonkuy.data.source.remote.response.ResultsItemListTvShow
import com.example.nontonkuy.retrofit.ApiConfig
import com.example.nontonkuy.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowListViewModel(
        private val tvShowRepository: TvShowRepository
): ViewModel() {

    fun getTvShows() = tvShowRepository.getTvShows()


}