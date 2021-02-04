package com.example.nontonkuy.ui.tvshow

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.ResponseListTvShow
import com.example.nontonkuy.data.ResultsItemListTvShow
import com.example.nontonkuy.retrofit.ApiConfig
import com.example.nontonkuy.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowListViewModel: ViewModel() {

    private val listTvShow = MutableLiveData<ArrayList<ResultsItemListTvShow>>()

    private lateinit var client: Call<ResponseListTvShow>

    companion object {
        private const val TAG = "TvShowListViewModel"
    }

    fun setListTvShow(mContext: Context?){
        EspressoIdlingResource.increment()
        client = ApiConfig.getApiService().getPopularTvShow()
        client.enqueue(object : Callback<ResponseListTvShow>{
            override fun onFailure(call: Call<ResponseListTvShow>, t: Throwable) {
//                Log.d(TAG, "onFailure: ${t.message.toString()}")
                Toast.makeText(mContext,"onFailure: ${t.message.toString()}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseListTvShow>,
                response: Response<ResponseListTvShow>
            ) {
                if (response.isSuccessful){
                    EspressoIdlingResource.decrement()
//                    listTvShow.postValue(response.body()?.results)
                    listTvShow.value = response.body()?.results
                }
                else
//                    Log.d(TAG, "onFailure: ${response.message()}")
                Toast.makeText(mContext,"onFailure: ${response.message()}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getListTvShow(): LiveData<ArrayList<ResultsItemListTvShow>>{
        return listTvShow
    }

}