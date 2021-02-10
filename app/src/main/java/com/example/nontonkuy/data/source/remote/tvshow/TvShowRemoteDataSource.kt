package com.example.nontonkuy.data.source.remote.tvshow

import android.util.Log
import com.example.nontonkuy.data.source.remote.response.ResponseDetailTvShow
import com.example.nontonkuy.data.source.remote.response.ResponseListTvShow
import com.example.nontonkuy.data.source.remote.response.ResultsItemListTvShow
import com.example.nontonkuy.retrofit.ApiConfig
import com.example.nontonkuy.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowRemoteDataSource {

    companion object {
        @Volatile
        private var instance: TvShowRemoteDataSource? = null

        fun getInstance(): TvShowRemoteDataSource =
                instance
                        ?: synchronized(this){
                    instance
                            ?: TvShowRemoteDataSource()
                }
    }

    fun getTvShows(callback: LoadTvShowsCallback){
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getPopularTvShow()
        client.enqueue(object : Callback<ResponseListTvShow>{
            override fun onFailure(call: Call<ResponseListTvShow>, t: Throwable) {
                EspressoIdlingResource.decrement()
                Log.d("TvShowRemoteDataSource", "onFailure: ${t.message.toString()}")
            }

            override fun onResponse(call: Call<ResponseListTvShow>, response: Response<ResponseListTvShow>) {
                if (response.isSuccessful){
                    EspressoIdlingResource.decrement()
                    callback.onTvShowsLoaded(response.body()?.results)
                }
            }
        })
    }

    fun getDetailTvShow(idTvShow: String?, callback: LoadDetailTvShowCallback){
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailTvShow(idTvShow)
        client.enqueue(object : Callback<ResponseDetailTvShow>{
            override fun onFailure(call: Call<ResponseDetailTvShow>, t: Throwable) {
                EspressoIdlingResource.decrement()
                Log.d("TvShowRemoteDataSource", t.message.toString())
            }

            override fun onResponse(call: Call<ResponseDetailTvShow>, response: Response<ResponseDetailTvShow>) {
                if (response.isSuccessful){
                    EspressoIdlingResource.decrement()
                    callback.onDetailTvShowLoaded(response.body())
                } else {
                    EspressoIdlingResource.decrement()
                    Log.d("TvShowRemoteDataSource", response.message())
                }
            }
        })
    }

    fun getRecomendation(idTvShow: String?, callback : LoadRecomendationTvShowCallback){
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getRecomendationTvShow(idTvShow)
        client.enqueue(object : Callback<ResponseListTvShow>{
            override fun onFailure(call: Call<ResponseListTvShow>, t: Throwable) {
                Log.d("TvShowRemoteDataSource", t.message.toString())
            }

            override fun onResponse(call: Call<ResponseListTvShow>, response: Response<ResponseListTvShow>) {
                if (response.isSuccessful){
                    EspressoIdlingResource.decrement()
                    callback.onRecomendationiLoaded(response.body()?.results)
                } else {
                    EspressoIdlingResource.decrement()
                    Log.d("TvShowRemoteDataSource", response.message())
                }
            }
        })
    }

    interface LoadRecomendationTvShowCallback {
        fun onRecomendationiLoaded(recomendationTvShow: ArrayList<ResultsItemListTvShow>?)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowLoaded(movie: ResponseDetailTvShow?)
    }

    interface LoadTvShowsCallback {
        fun onTvShowsLoaded(listTvShow: ArrayList<ResultsItemListTvShow>?)
    }

}