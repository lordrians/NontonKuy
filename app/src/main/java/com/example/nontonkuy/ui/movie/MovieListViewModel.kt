package com.example.nontonkuy.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.ResponseListMovie
import com.example.nontonkuy.data.ResultsItemListMovie
import com.example.nontonkuy.retrofit.ApiConfig
import com.example.nontonkuy.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModel : ViewModel() {

    private val listMovie = MutableLiveData<ArrayList<ResultsItemListMovie>>()

    private lateinit var client: Call<ResponseListMovie>

    companion object{
        private const val TAG = "MovieListViewModel"
    }

    fun setListMovie(){
        EspressoIdlingResource.increment()
        client = ApiConfig.getApiService().getPopularMovie()
        client.enqueue(object : Callback<ResponseListMovie>{
            override fun onFailure(call: Call<ResponseListMovie>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }

            override fun onResponse(
                call: Call<ResponseListMovie>,
                response: Response<ResponseListMovie>
            ) {
                if (response.isSuccessful){
                    EspressoIdlingResource.decrement()
                    listMovie.postValue(response.body()?.results)
                }
                else {
                    Log.d(TAG, "onFailure: ${response.message()}")
                }
            }
        })
    }

    fun getListMovie(): LiveData<ArrayList<ResultsItemListMovie>>{
        return listMovie
    }


}