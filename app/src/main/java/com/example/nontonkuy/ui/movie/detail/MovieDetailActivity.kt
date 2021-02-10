package com.example.nontonkuy.ui.movie.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nontonkuy.BuildConfig
import com.example.nontonkuy.R
import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.databinding.ActivityMovieDetailBinding
import com.example.nontonkuy.ui.adapter.RecomendationMovieAdapter
import com.example.nontonkuy.ui.movie.MovieViewModelFactory
import com.example.nontonkuy.utils.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var adapter: RecomendationMovieAdapter

    private var idMovie: Int? = null

    companion object {
        const val ID_MOVIE = "id_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idMovie = intent?.getIntExtra(ID_MOVIE,0)


        loadDetail()
        loadRecomendation()


    }

    private fun loadDetail() {
        val factory = MovieViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MovieDetailViewModel::class.java]

        viewModel.getDetailMovies(idMovie.toString()).observe(this) { detail ->
            when (detail.status){
                Status.LOADING -> showProgressBar(true)
                Status.SUCCESS -> {
                    if (detail.data != null){
                        showProgressBar(false)
                        fillingData(detail.data)
                    }
                }
                Status.ERROR -> {
                    showProgressBar(false)
                    Toast.makeText(applicationContext, resources.getString(R.string.there_is_no_data_laoded), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fillingData(detailMovie: MovieEntity) {
        with(binding){

            tvMovdetailTitle.text = detailMovie.originalTitle

            tvMovdetailGenre.text = detailMovie.genres

            tvMovdetailDate.text = detailMovie.releaseDate
            tvMovdetailRuntime.text = detailMovie.runtime.toString() + " min"
            tvMovdetailBudget.text = detailMovie.budget.toString()
            tvMovdetailRevenue.text = detailMovie.revenue.toString()
            tvMovdetailDesc.text = detailMovie.overview

            ivMovdetailBanner.let {
                Glide.with(root.context)
                        .load("${BuildConfig.PATH_ORIGINAL_IMG}${detailMovie.backdropPath}")
                        .into(it)
            }

            ivMovdetailPoster.let {
                Glide.with(root.context)
                        .load("${BuildConfig.PATH_IMG}${detailMovie.posterPath}")
                        .into(it)
            }

        }
    }


    private fun loadRecomendation() {
        val factory = MovieViewModelFactory.getInstance(this)
        val viewModels = ViewModelProvider(this, factory)[MovieDetailViewModel::class.java]

        viewModels.getRecomendationMovie(idMovie.toString()).observe(this){listRecomendation ->
            adapter = RecomendationMovieAdapter(listRecomendation)
            adapter.notifyDataSetChanged()
            binding.rvRecomendedMov.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.rvRecomendedMov.adapter = adapter

            setVisible(binding.rvRecomendedMov)
            setGone(binding.pbMovieDetailRec)
        }
    }

    private fun showProgressBar(state: Boolean){
        binding.rvRecomendedMov.isInvisible = state
        binding.pbMovieDetail.isVisible = state
    }

}