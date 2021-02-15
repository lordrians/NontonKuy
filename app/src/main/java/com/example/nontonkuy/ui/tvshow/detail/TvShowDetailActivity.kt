package com.example.nontonkuy.ui.tvshow.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nontonkuy.BuildConfig
import com.example.nontonkuy.R
import com.example.nontonkuy.data.source.local.entity.TvShowEntity
import com.example.nontonkuy.databinding.ActivityTvShowDetailBinding
import com.example.nontonkuy.ui.adapter.RecomendationTvShowAdapter
import com.example.nontonkuy.utils.Status
import com.example.nontonkuy.ui.tvshow.TvShowViewModelFactory
import com.example.nontonkuy.utils.setGone
import com.example.nontonkuy.utils.setVisible

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowDetailBinding
    private lateinit var adapter: RecomendationTvShowAdapter
    private lateinit var viewModel : TvShowDetailViewModel

    private var idTvShow: Int? = null
    private var menuItem : Menu? = null

    companion object {
        const val ID_TVSHOW = "id_tvshow"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.tbDetail)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        idTvShow = intent?.getIntExtra(ID_TVSHOW, 0)

        loadDetail()
        loadRecomendation()

    }

    private fun loadDetail() {
        val factory = TvShowViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[TvShowDetailViewModel::class.java]

        viewModel.getDetailTvShow(idTvShow.toString()).observe(this){ detail ->
            when (detail.status){
                Status.LOADING -> showProgressBar(true)
                Status.SUCCESS -> {
                    if (detail.data != null){
                        showProgressBar(false)
                        fillingData(detail.data)
                        val state = detail.data.isFav
                        setupFavState(state)
                    }
                }
                Status.ERROR -> {
                    showProgressBar(false)
                    Toast.makeText(applicationContext, resources.getString(R.string.there_is_no_data_laoded), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setupFavState(state: Boolean) {
        if (state)
            menuItem?.getItem(0)?.icon = resources.getDrawable(R.drawable.ic_favorite_fill)
        else
            menuItem?.getItem(0)?.icon = resources.getDrawable(R.drawable.ic_favorite_border)
    }

    @SuppressLint("SetTextI18n")
    private fun fillingData(tvShow: TvShowEntity) {
        with(binding){

            tvTvshowTitle.text = tvShow.originalName
            tvTvshowDate.text = tvShow.firstAirDate
            tvTvshowRuntime.text = tvShow.episodeRunTime.toString() + " min"
            tvTvshowSeasonEps.text = "${tvShow.numberOfSeasons.toString()} / ${tvShow.numberOfEpisodes.toString()}"
            tvTvshowStatus.text = tvShow.status
            tvTvshowDesc.text = tvShow.overview
            tvTvshowGenre.text = tvShow.genres

            Glide.with(root.context)
                    .load("${BuildConfig.PATH_ORIGINAL_IMG}${tvShow.backdropPath}")
                    .into(ivTvshowdetailBanner)

            Glide.with(root.context)
                    .load("${BuildConfig.PATH_IMG}${tvShow.posterPath}")
                    .into(ivTvshowdetailPoster)
        }
    }

    private fun showProgressBar(state: Boolean) {
        binding.rvRecomendedTvshow.isInvisible = state
        binding.pbTvshowDetail.isVisible = state
    }

    private fun loadRecomendation() {
        val factory = TvShowViewModelFactory.getInstance(this)
        val viewModels = ViewModelProvider(this, factory)[TvShowDetailViewModel::class.java]

        viewModels.getRecomendationTvShow(idTvShow.toString()).observe(this){ listRecomendation ->
            if (listRecomendation.size > 0){
                adapter = RecomendationTvShowAdapter(listRecomendation)
                adapter.notifyDataSetChanged()

                binding.rvRecomendedTvshow.setHasFixedSize(true)
                binding.rvRecomendedTvshow.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                binding.rvRecomendedTvshow.adapter = adapter

                setVisible(binding.rvRecomendedTvshow)
                setGone(binding.pbTvshowDetailRec)
            } else {
                setGone(binding.pbTvshowDetailRec)
                Toast.makeText(this,"onFailure:" + resources.getString(R.string.there_is_no_data_laoded) + "on recemendation", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        this.menuItem = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> onBackPressed()
            R.id.action_favorite -> {
                viewModel.setFavoriteTvShow()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}