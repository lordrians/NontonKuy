package com.example.nontonkuy.ui.movie

import android.util.Log
import com.example.nontonkuy.data.ResponseListMovie
import com.example.nontonkuy.retrofit.ApiConfig
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModelTest{

    private lateinit var client: Call<ResponseListMovie>

    @Before
    fun setUp(){
        client = ApiConfig.getApiService().getPopularMovie()
    }

    @Test
    fun getMovieList(){

        client.enqueue(object : Callback<ResponseListMovie>{
            override fun onFailure(call: Call<ResponseListMovie>, t: Throwable) {
                Log.d("MovieListViewModelTest", "onFailure: ${t.message.toString()}")
            }

            override fun onResponse(call: Call<ResponseListMovie>, response: Response<ResponseListMovie>) {
                assertNotNull(response.body()?.results)
                assertEquals(20, response.body()?.results?.size)
            }
        })


    }

}