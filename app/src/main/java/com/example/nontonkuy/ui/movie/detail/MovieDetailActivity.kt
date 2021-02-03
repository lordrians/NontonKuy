package com.example.nontonkuy.ui.movie.detail

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nontonkuy.BuildConfig
import com.example.nontonkuy.data.ResponseListMovie
import com.example.nontonkuy.data.ResultsItemListMovie
import com.example.nontonkuy.databinding.ActivityMovieDetailBinding
import com.example.nontonkuy.ui.adapter.MovieListAdapter
import com.example.nontonkuy.ui.adapter.RecomendationMovieAdapter
import com.example.nontonkuy.utils.setGone
import com.example.nontonkuy.utils.setVisible

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

        viewModel = MovieDetailViewModel()

        fillingData()

        settingRecomendation()


    }

    private fun settingRecomendation() {
        viewModel.setRecomendationsMovie(idMovie.toString())
        viewModel.getRecomendationsMovie().observe(this, Observer { listRecomendation ->
            if (listRecomendation.size > 0){
                adapter = RecomendationMovieAdapter(listRecomendation)
                adapter.notifyDataSetChanged()

                binding.rvRecomendedMov.setHasFixedSize(true)
                binding.rvRecomendedMov.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                binding.rvRecomendedMov.adapter = adapter

                adapter.setOnItemClickCallback(object : RecomendationMovieAdapter.OnItemClickCallback{
                    override fun onItemClicked(data: ResultsItemListMovie) {
                        val intent = Intent(applicationContext, MovieDetailActivity::class.java)
                        intent.putExtra(ID_MOVIE, data.id)
                        startActivity(intent)
                    }
                })

                setVisible(binding.rvRecomendedMov)
                setGone(binding.pbMovieDetailRec)

            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun fillingData() {
        viewModel.setMovieDetail(idMovie.toString())
        viewModel.getDetailMovie().observe(this, Observer { movies ->
            if (movies != null){
                with(binding){
                    var genres = ""
                    tvMovdetailTitle.text = movies.originalTitle

                    if (movies.genres?.size != 0) {
                        for (item in movies.genres!!){
                            genres += item?.name + "; "
                        }
                    }
                    tvMovdetailGenre.text = genres

                    tvMovdetailDate.text = movies.releaseDate
                    tvMovdetailRuntime.text = movies.runtime.toString() + "m"
                    tvMovdetailBudget.text = movies.budget.toString()
                    tvMovdetailRevenue.text = movies.revenue.toString()
                    tvMovdetailDesc.text = movies.overview

                    ivMovdetailBanner.let {
                        Glide.with(root.context)
                                .load("${BuildConfig.PATH_ORIGINAL_IMG}${movies.backdropPath}")
                                .into(it)
                    }

                    ivMovdetailPoster.let {
                        Glide.with(root.context)
                                .load("${BuildConfig.PATH_IMG}${movies.posterPath}")
                                .into(it)
                    }

                    setVisible(binding.containerMovieDetail)
                    setGone(binding.pbMovieDetail)

                }
            }
        })
    }
}