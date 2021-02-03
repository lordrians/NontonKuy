package com.example.nontonkuy.ui.tvshow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.ResponseListTvShow
import com.example.nontonkuy.data.ResultsItemListTvShow
import com.example.nontonkuy.retrofit.ApiConfig
import com.example.nontonkuy.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowListViewModel: ViewModel() {

    private val listTvShow = MutableLiveData<ArrayList<ResultsItemListTvShow>>()

    private lateinit var client: Call<ResponseListTvShow>

    companion object {
        private const val TAG = "TvShowListViewModel"
    }

    fun setListTvShow(){
        EspressoIdlingResource.increment()
        client = ApiConfig.getApiService().getPopularTvShow()
        client.enqueue(object : Callback<ResponseListTvShow>{
            override fun onFailure(call: Call<ResponseListTvShow>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }

            override fun onResponse(
                call: Call<ResponseListTvShow>,
                response: Response<ResponseListTvShow>
            ) {
                if (response.isSuccessful){
                    EspressoIdlingResource.decrement()
                    listTvShow.postValue(response.body()?.results)
                }
                else
                    Log.d(TAG, "onFailure: ${response.message()}")
            }
        })
    }

    fun getListTvShow(): LiveData<ArrayList<ResultsItemListTvShow>>{
        return listTvShow
    }

}