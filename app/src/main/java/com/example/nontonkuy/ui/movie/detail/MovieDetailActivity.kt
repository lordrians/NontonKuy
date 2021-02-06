package com.example.nontonkuy.ui.movie.detail

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nontonkuy.BuildConfig
import com.example.nontonkuy.R
import com.example.nontonkuy.data.source.remote.response.ResultsItemListMovie
import com.example.nontonkuy.databinding.ActivityMovieDetailBinding
import com.example.nontonkuy.ui.adapter.MovieListAdapter
import com.example.nontonkuy.ui.adapter.RecomendationMovieAdapter
import com.example.nontonkuy.utils.MovieViewModelFactory
import com.example.nontonkuy.utils.setGone
import com.example.nontonkuy.utils.setGridPixel
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


        loadDetail()
        loadRecomendation()


    }

    private fun loadRecomendation() {
        val factory = MovieViewModelFactory.getInstance(this)
        val viewModels = ViewModelProvider(this, factory)[MovieDetailViewModel::class.java]

        viewModels.getRecomendationMovie(idMovie.toString()).observe(this){listRecomendation ->
            adapter = RecomendationMovieAdapter(listRecomendation)
            adapter.notifyDataSetChanged()
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
    }

    @SuppressLint("SetTextI18n")
    private fun loadDetail() {
        val factory = MovieViewModelFactory.getInstance(this)
        val viewModels =  ViewModelProvider(this, factory)[MovieDetailViewModel::class.java]

        viewModels.getDetailMovie(idMovie.toString()).observe(this){detailMovie ->
            if (detailMovie != null){
                with(binding){
                    var genres = ""
                    tvMovdetailTitle.text = detailMovie.originalTitle

                    if (detailMovie.genres?.size != 0) {
                        for (item in detailMovie.genres!!){
                            genres += item?.name + "; "
                        }
                    }
                    tvMovdetailGenre.text = genres

                    tvMovdetailDate.text = detailMovie.releaseDate
                    tvMovdetailRuntime.text = detailMovie.runtime.toString() + "m"
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

                    setVisible(binding.containerMovieDetail)
                    setGone(binding.pbMovieDetail)
                }
            } else {
                setVisible(binding.containerMovieDetail)
                setGone(binding.pbMovieDetail)
                Toast.makeText(this,"onFailure:" + resources.getString(R.string.there_is_no_data_laoded), Toast.LENGTH_SHORT).show()
            }
        }
    }

}