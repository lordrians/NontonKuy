package com.example.nontonkuy.ui.movie.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nontonkuy.R
import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.databinding.FragmentMovieFavoriteBinding
import com.example.nontonkuy.ui.adapter.MovieAdapter
import com.example.nontonkuy.ui.movie.MovieViewModelFactory
import com.example.nontonkuy.ui.movie.detail.MovieDetailActivity
import com.example.nontonkuy.ui.movie.detail.MovieDetailActivity.Companion.ID_MOVIE
import com.example.nontonkuy.utils.setGone
import com.example.nontonkuy.utils.setGridPixel
import com.example.nontonkuy.utils.setVisible

class MovieFavoriteFragment : Fragment(),MovieAdapter.OnItemClickCallback {

    private lateinit var binding: FragmentMovieFavoriteBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()

    }

    private fun loadData() {
        val factory = MovieViewModelFactory.getInstance(requireContext())
        val viewModel = ViewModelProvider(this, factory)[MovieFavoriteViewModel::class.java]

        movieAdapter = MovieAdapter()
        viewModel.getFavoriteMovie().observe(viewLifecycleOwner, Observer { listFavorite ->
            if (listFavorite != null){
                showProgressBar(false)
                movieAdapter.submitList(listFavorite)
                movieAdapter.setOnItemClickCallback(this)
                movieAdapter.notifyDataSetChanged()
            }

        })

        binding.rvFavMovie.layoutManager = context?.let { setGridPixel(it) }?.let { GridLayoutManager(context, it) }
        binding.rvFavMovie.adapter = movieAdapter
        binding.rvFavMovie.setHasFixedSize(true)

    }

    private fun showProgressBar(state: Boolean) {
        binding.pbFavMov.isVisible = state
        binding.rvFavMovie.isInvisible = state
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    override fun onItemClicked(data: MovieEntity) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(ID_MOVIE, data.id)
        context?.startActivity(intent)
    }
}