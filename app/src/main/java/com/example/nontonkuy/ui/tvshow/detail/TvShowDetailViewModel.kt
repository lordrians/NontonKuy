package com.example.nontonkuy.ui.tvshow.detail

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nontonkuy.data.ResponseDetailTvShow
import com.example.nontonkuy.data.ResponseListTvShow
import com.example.nontonkuy.data.ResultsItemListTvShow
import com.example.nontonkuy.retrofit.ApiConfig
import com.example.nontonkuy.ui.movie.detail.MovieDetailViewModel
import com.example.nontonkuy.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowDetailViewModel: ViewModel() {

    private val tvshow = MutableLiveData<ResponseDetailTvShow>()
    private val recTvShow = MutableLiveData<ArrayList<ResultsItemListTvShow>>()

    private lateinit var clientDetail : Call<ResponseDetailTvShow>
    private lateinit var clientRec : Call<ResponseListTvShow>

    companion object {
        private const val TAG = "TvShowDetailViewModel"
    }

    fun setTvShowDetail(mContext: Context?, idTvShow: String?){
        EspressoIdlingResource.increment()
        clientDetail = ApiConfig.getApiService().getDetailTvShow(idTvShow)
        clientDetail.enqueue(object : Callback<ResponseDetailTvShow>{
            override fun onFailure(call: Call<ResponseDetailTvShow>, t: Throwable) {
//                Log.d(TAG, "onFailure: ${t.message.toString()}")
                Toast.makeText(mContext,"onFailure: ${t.message.toString()}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseDetailTvShow>,
                response: Response<ResponseDetailTvShow>
            ) {
                if (response.isSuccessful){
                    EspressoIdlingResource.decrement()
//                    tvshow.postValue(response.body())
                    tvshow.value = response.body()
                }
                else
                    Toast.makeText(mContext,"onFailure: ${response.message()}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getDetailTvShow(): LiveData<ResponseDetailTvShow>{
        return tvshow
    }

    fun setRecomendationTvShow(mContext: Context?, idTvShow: String?){
        EspressoIdlingResource.increment()
        clientRec = ApiConfig.getApiService().getRecomendationTvShow(idTvShow)
        clientRec.enqueue(object : Callback<ResponseListTvShow>{
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
//                    recTvShow.postValue(response.body()?.results)
                    recTvShow.value = response.body()?.results
                }
                else
                    Toast.makeText(mContext,"onFailure: ${response.message()}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getRecomendationTvShow(): LiveData<ArrayList<ResultsItemListTvShow>>{
        return recTvShow
    }

}