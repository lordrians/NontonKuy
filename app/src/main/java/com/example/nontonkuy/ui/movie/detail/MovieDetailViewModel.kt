package com.example.nontonkuy.ui.movie.detail

import android.content.Context
import android.util.Log
import android.widget.Toast
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

    fun setMovieDetail(mContext: Context?,idMovie: String?){
        EspressoIdlingResource.increment()
        clientDetail = ApiConfig.getApiService().getDetailMovie(idMovie)
        clientDetail.enqueue(object : Callback<ResponseDetailMovie>{
            override fun onFailure(call: Call<ResponseDetailMovie>, t: Throwable) {
//                Log.d(TAG, "onFailure: ${t.message.toString()}")
                Toast.makeText(mContext,"onFailure: ${t.message.toString()}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseDetailMovie>,
                response: Response<ResponseDetailMovie>
            ) {
                if (response.isSuccessful){
                    EspressoIdlingResource.decrement()
//                    movie.postValue(response.body())
                    movie.value = response.body()
                }
                else
                    Toast.makeText(mContext,"onFailure: ${response.message()}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getDetailMovie(): LiveData<ResponseDetailMovie>{
        return movie
    }

    fun setRecomendationsMovie(mContext: Context?,idMovie: String?){
        EspressoIdlingResource.increment()
        client = ApiConfig.getApiService().getRecomendationMovie(idMovie)
        client.enqueue(object : Callback<ResponseListMovie>{
            override fun onFailure(call: Call<ResponseListMovie>, t: Throwable) {
//                Log.d(TAG, "onFailure: ${t.message.toString()}")
                Toast.makeText(mContext,"onFailure: ${t.message.toString()}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseListMovie>, response: Response<ResponseListMovie>) {
                if (response.isSuccessful){
                    EspressoIdlingResource.decrement()
//                    recMovies.postValue(response.body()?.results)
                    recMovies.value = response.body()?.results
                }
                else
                    Toast.makeText(mContext,"onFailure: ${response.message()}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getRecomendationsMovie(): LiveData<ArrayList<ResultsItemListMovie>>{
        return recMovies
    }

}