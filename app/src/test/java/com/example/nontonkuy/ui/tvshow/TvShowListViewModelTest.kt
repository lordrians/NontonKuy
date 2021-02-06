package com.example.nontonkuy.ui.tvshow

import android.util.Log
import com.example.nontonkuy.data.source.remote.response.ResponseListTvShow
import com.example.nontonkuy.retrofit.ApiConfig
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowListViewModelTest {

    private lateinit var client: Call<ResponseListTvShow>

    @Before
    fun setUp(){
        client = ApiConfig.getApiService().getPopularTvShow()
    }

    @Test
    fun getTvShowList(){
        client.enqueue(object : Callback<ResponseListTvShow>{
            override fun onFailure(call: Call<ResponseListTvShow>, t: Throwable) {
                Log.d("TvShowListViewModelTest", "onFailure: ${t.message.toString()}")
            }

            override fun onResponse(call: Call<ResponseListTvShow>, response: Response<ResponseListTvShow>) {
                assertNotNull(response.body()?.results)
                assertEquals(20, response.body()?.results?.size)
            }
        })
    }

}