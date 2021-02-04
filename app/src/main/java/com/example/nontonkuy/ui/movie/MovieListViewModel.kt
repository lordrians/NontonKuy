package com.example.nontonkuy.ui.movie

import android.content.Context
import android.util.Log
import android.widget.Toast
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

    fun setListMovie(mContext: Context?){
        EspressoIdlingResource.increment()
        client = ApiConfig.getApiService().getPopularMovie()
        client.enqueue(object : Callback<ResponseListMovie>{
            override fun onFailure(call: Call<ResponseListMovie>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message.toString()}")
                Toast.makeText(mContext,"onFailure: ${t.message.toString()}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseListMovie>,
                response: Response<ResponseListMovie>
            ) {
                if (response.isSuccessful){
                    EspressoIdlingResource.decrement()
//                    listMovie.postValue(response.body()?.results)
                    listMovie.value = response.body()?.results
                }
                else {
                    Toast.makeText(mContext,"onFailure: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun getListMovie(): LiveData<ArrayList<ResultsItemListMovie>>{
        return listMovie
    }


}