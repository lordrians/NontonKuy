package com.example.nontonkuy.data.source.remote.movie

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nontonkuy.data.source.remote.ApiResponse
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

    fun getMovies(callback: LoadMoviesCallback){
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getPopularMovie()
        client.enqueue(object : Callback<ResponseListMovie>{
            override fun onFailure(call: Call<ResponseListMovie>, t: Throwable) {
                EspressoIdlingResource.decrement()
                Log.d("MovieRemoteDataSource", "onFailure: ${t.message.toString()}")
            }

            override fun onResponse(call: Call<ResponseListMovie>, response: Response<ResponseListMovie>) {
                EspressoIdlingResource.decrement()
                callback.onMoviesLoaded(response.body()?.results)
            }
        })
    }

    fun getDetailMovie(idMovie: String?, callback: LoadDetailMovieCallback){
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailMovie(idMovie)
        client.enqueue(object : Callback<ResponseDetailMovie>{
            override fun onFailure(call: Call<ResponseDetailMovie>, t: Throwable) {
                EspressoIdlingResource.decrement()
                Log.d("MovieRemoteData", t.message.toString())
            }

            override fun onResponse(call: Call<ResponseDetailMovie>, response: Response<ResponseDetailMovie>) {
                if (response.isSuccessful){
                    EspressoIdlingResource.decrement()
                    callback.onDetailMovieLoaded(response.body())
                } else {
                    EspressoIdlingResource.decrement()
                    Log.d("MovieRemoteData", response.message())
                }
            }
        })
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

    interface LoadDetailMovieCallback {
        fun onDetailMovieLoaded(movie: ResponseDetailMovie?)
    }

    interface LoadMoviesCallback {
        fun onMoviesLoaded(movies: ArrayList<ResultsItemListMovie>?)
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
                resultMovies.value = ApiResponse.success(response.body()?.results as List<ResultsItemListMovie>)
                EspressoIdlingResource.decrement()
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
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<ResponseDetailMovie>,
                response: Response<ResponseDetailMovie>
            ) {
                resultDetailMovie.value = ApiResponse.success(response.body() as ResponseDetailMovie)
            }
        })
        return resultDetailMovie
    }

}