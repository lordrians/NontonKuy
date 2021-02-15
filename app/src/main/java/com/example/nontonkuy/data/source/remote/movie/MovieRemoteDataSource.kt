package com.example.nontonkuy.data.source.remote.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nontonkuy.utils.ApiResponse
import com.example.nontonkuy.data.source.remote.response.ResponseDetailMovie
import com.example.nontonkuy.data.source.remote.response.ResponseListMovie
import com.example.nontonkuy.data.source.remote.response.ResultsItemListMovie
import com.example.nontonkuy.retrofit.ApiConfig
import com.example.nontonkuy.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteDataSource {

    companion object {
        @Volatile
        private var instance: MovieRemoteDataSource? = null

        fun getInstance(): MovieRemoteDataSource =
                instance
                        ?: synchronized(this){
                    instance
                            ?: MovieRemoteDataSource()
                }
    }

    fun getRecomendationMovie(idMovie: String?, callback: LoadRecomendationMovieCallback){
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getRecomendationMovie(idMovie)
        client.enqueue(object : Callback<ResponseListMovie>{
            override fun onFailure(call: Call<ResponseListMovie>, t: Throwable) {
                Log.d("MovieRemoteData", t.message.toString())
            }

            override fun onResponse(call: Call<ResponseListMovie>, response: Response<ResponseListMovie>) {
                if (response.isSuccessful){
                    EspressoIdlingResource.decrement()
                    callback.onRecomendationLoaded(response.body()?.results)
                } else {
                    EspressoIdlingResource.decrement()
                    Log.d("MovieRemoteData", response.message())
                }
            }
        })
    }

    interface LoadRecomendationMovieCallback {
        fun onRecomendationLoaded(recomendationMovie: ArrayList<ResultsItemListMovie>?)
    }


    fun getListMovie(): LiveData<ApiResponse<List<ResultsItemListMovie>>>{
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<ResultsItemListMovie>>>()
        val client = ApiConfig.getApiService().getPopularMovie()

        client.enqueue(object : Callback<ResponseListMovie>{
            override fun onFailure(call: Call<ResponseListMovie>, t: Throwable) {
                Log.e("MovieRemoteDataSource", "onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }

            override fun onResponse(
                call: Call<ResponseListMovie>,
                response: Response<ResponseListMovie>
            ) {
                EspressoIdlingResource.decrement()
                resultMovies.value = ApiResponse.success(response.body()?.results as List<ResultsItemListMovie>)
            }
        })
        return resultMovies
    }

    fun getDetailMovies(idMovie: String): LiveData<ApiResponse<ResponseDetailMovie>>{
        EspressoIdlingResource.increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<ResponseDetailMovie>>()
        val client = ApiConfig.getApiService().getDetailMovie(idMovie)

        client.enqueue(object : Callback<ResponseDetailMovie>{
            override fun onFailure(call: Call<ResponseDetailMovie>, t: Throwable) {
                EspressoIdlingResource.decrement()
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<ResponseDetailMovie>,
                response: Response<ResponseDetailMovie>
            ) {
                EspressoIdlingResource.decrement()
                resultDetailMovie.value = ApiResponse.success(response.body() as ResponseDetailMovie)
            }
        })
        return resultDetailMovie
    }

}