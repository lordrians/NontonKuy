package com.example.nontonkuy.ui.movie.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.ResponseDetailMovie
import com.example.nontonkuy.data.ResponseListMovie
import com.example.nontonkuy.data.ResultsItemListMovie
import com.example.nontonkuy.retrofit.ApiConfig
import com.example.nontonkuy.ui.movie.MovieListViewModel
import com.example.nontonkuy.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel: ViewModel() {

    private val movie = MutableLiveData<ResponseDetailMovie>()
    private val recMovies = MutableLiveData<ArrayList<ResultsItemListMovie>>()

    private lateinit var clientDetail: Call<ResponseDetailMovie>
    private lateinit var client: Call<ResponseListMovie>

    companion object {
        private const val TAG = "MovieDetailViewModel"
    }

    fun setMovieDetail(idMovie: String?){
        EspressoIdlingResource.increment()
        clientDetail = ApiConfig.getApiService().getDetailMovie(idMovie)
        clientDetail.enqueue(object : Callback<ResponseDetailMovie>{
            override fun onFailure(call: Call<ResponseDetailMovie>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }

            override fun onResponse(
                call: Call<ResponseDetailMovie>,
                response: Response<ResponseDetailMovie>
            ) {
                if (response.isSuccessful){
                    EspressoIdlingResource.decrement()
                    movie.postValue(response.body())
                }
                else
                    Log.d(TAG, "onFailure: ${response.message()}")
            }
        })
    }

    fun getDetailMovie(): LiveData<ResponseDetailMovie>{
        return movie
    }

    fun setRecomendationsMovie(idMovie: String?){
        EspressoIdlingResource.increment()
        client = ApiConfig.getApiService().getRecomendationMovie(idMovie)
        client.enqueue(object : Callback<ResponseListMovie>{
            override fun onFailure(call: Call<ResponseListMovie>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }

            override fun onResponse(call: Call<ResponseListMovie>, response: Response<ResponseListMovie>) {
                if (response.isSuccessful){
                    EspressoIdlingResource.decrement()
                    recMovies.postValue(response.body()?.results)
                }
                else
                    Log.d(TAG, "onFailure: ${response.message()}")
            }
        })
    }

    fun getRecomendationsMovie(): LiveData<ArrayList<ResultsItemListMovie>>{
        return recMovies
    }

}