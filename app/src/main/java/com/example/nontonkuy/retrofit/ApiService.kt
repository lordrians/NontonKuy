package com.example.nontonkuy.retrofit

import com.example.nontonkuy.BuildConfig
import com.example.nontonkuy.data.ResponseDetailMovie
import com.example.nontonkuy.data.ResponseDetailTvShow
import com.example.nontonkuy.data.ResponseListMovie
import com.example.nontonkuy.data.ResponseListTvShow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("${BuildConfig.BASE_URL}movie/popular?api_key=${BuildConfig.API_KEY}&language=en-US&page=1")
    fun getPopularMovie(): Call<ResponseListMovie>

    @GET("${BuildConfig.BASE_URL}tv/popular?api_key=${BuildConfig.API_KEY}&language=en-US&page=1")
    fun getPopularTvShow(): Call<ResponseListTvShow>

    @GET("${BuildConfig.BASE_URL}movie/{idMovie}?api_key=${BuildConfig.API_KEY}&language=en-US")
    fun getDetailMovie(
        @Path("idMovie") idMovie: String?
    ): Call<ResponseDetailMovie>

    @GET("${BuildConfig.BASE_URL}movie/{idMovie}/recommendations?api_key=${BuildConfig.API_KEY}&language=en-US&page=1")
    fun getRecomendationMovie(
            @Path("idMovie") idMovie: String?
    ): Call<ResponseListMovie>

    @GET("${BuildConfig.BASE_URL}tv/{idTvShow}/recommendations?api_key=${BuildConfig.API_KEY}&language=en-US&page=1")
    fun getRecomendationTvShow(
        @Path("idTvShow") idTvShow: String?
    ): Call<ResponseListTvShow>

    @GET("${BuildConfig.BASE_URL}tv/{idTvShow}?api_key=${BuildConfig.API_KEY}&language=en-US")
    fun getDetailTvShow(
        @Path("idTvShow") idTvShow: String?
    ): Call<ResponseDetailTvShow>
}