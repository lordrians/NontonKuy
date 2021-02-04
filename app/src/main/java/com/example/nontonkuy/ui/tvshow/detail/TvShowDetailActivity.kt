package com.example.nontonkuy.ui.tvshow.detail

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nontonkuy.BuildConfig
import com.example.nontonkuy.R
import com.example.nontonkuy.data.ResultsItemListTvShow
import com.example.nontonkuy.databinding.ActivityTvShowDetailBinding
import com.example.nontonkuy.ui.adapter.RecomendationTvShowAdapter
import com.example.nontonkuy.utils.setGone
import com.example.nontonkuy.utils.setVisible

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowDetailBinding
    private lateinit var viewModel: TvShowDetailViewModel
    private lateinit var adapter: RecomendationTvShowAdapter

    private var idTvShow: Int? = null

    companion object {
        const val ID_TVSHOW = "id_tvshow"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idTvShow = intent?.getIntExtra(ID_TVSHOW, 0)

        viewModel = TvShowDetailViewModel()

        fillingData()

        settingRecomendation()

    }

    private fun settingRecomendation() {
        viewModel.setRecomendationTvShow(this,idTvShow.toString())
        viewModel.getRecomendationTvShow().observe(this, Observer { listRecomendation ->
            if (listRecomendation.size > 0){
                adapter = RecomendationTvShowAdapter(listRecomendation)
                adapter.notifyDataSetChanged()

                binding.rvRecomendedTvshow.setHasFixedSize(true)
                binding.rvRecomendedTvshow.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                binding.rvRecomendedTvshow.adapter = adapter

                adapter.setOnItemClickCallback(object : RecomendationTvShowAdapter.OnItemClickCallback{
                    override fun onItemClicked(data: ResultsItemListTvShow) {
                        val intent = Intent(applicationContext, TvShowDetailActivity::class.java)
                        intent.putExtra(ID_TVSHOW, data.id)
                        startActivity(intent)
                    }
                })

                setVisible(binding.rvRecomendedTvshow)
                setGone(binding.pbTvshowDetailRec)
            } else {
                setGone(binding.pbTvshowDetailRec)
                Toast.makeText(this,"onFailure:" + resources.getString(R.string.there_is_no_data_laoded), Toast.LENGTH_SHORT).show()
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun fillingData() {
        viewModel.setTvShowDetail(this,idTvShow.toString())
        viewModel.getDetailTvShow().observe(this, Observer { tvShow ->
            if (tvShow != null){
                with(binding){
                    var genres = ""

                    tvTvshowTitle.text = tvShow.originalName
                    tvTvshowDate.text = tvShow.firstAirDate
                    tvTvshowRuntime.text = tvShow.episodeRunTime?.get(0).toString() + "m"
                    tvTvshowSeasonEps.text = "${tvShow.numberOfSeasons.toString()} / ${tvShow.numberOfEpisodes.toString()}"
                    tvTvshowStatus.text = tvShow.status
                    tvTvshowDesc.text = tvShow.overview

                    if (tvShow.genres?.size != 0){
                        for (item in tvShow.genres!!){
                            genres += item?.name + ": "
                        }
                        tvTvshowGenre.text = genres
                    }

                    Glide.with(root.context)
                        .load("${BuildConfig.PATH_ORIGINAL_IMG}${tvShow.backdropPath}")
                        .into(ivTvshowdetailBanner)

                    Glide.with(root.context)
                        .load("${BuildConfig.PATH_IMG}${tvShow.posterPath}")
                        .into(ivTvshowdetailPoster)

                    setVisible(containerTvshowDetail)
                    setGone(pbTvshowDetail)
                }
            } else {
                setGone(binding.pbTvshowDetail)
                Toast.makeText(this,"onFailure:" + resources.getString(R.string.there_is_no_data_laoded), Toast.LENGTH_SHORT).show()
            }
        })
    }
}