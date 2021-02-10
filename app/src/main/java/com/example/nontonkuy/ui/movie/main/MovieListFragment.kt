package com.example.nontonkuy.ui.movie.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nontonkuy.R
import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.databinding.FragmentMovieListBinding
import com.example.nontonkuy.ui.adapter.MovieAdapter
import com.example.nontonkuy.ui.movie.MovieViewModelFactory
import com.example.nontonkuy.ui.movie.detail.MovieDetailActivity
import com.example.nontonkuy.ui.movie.detail.MovieDetailActivity.Companion.ID_MOVIE
import com.example.nontonkuy.utils.*

class MovieListFragment : Fragment() , MovieAdapter.OnItemClickCallback{

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()


    }

    private fun loadData(){
        val factory = MovieViewModelFactory.getInstance(requireContext())
        val viewModels = ViewModelProvider(this, factory)[MovieListViewModel::class.java]

        movieAdapter = MovieAdapter()
        viewModels.getMovies().observe(viewLifecycleOwner, movieObserver)

        binding.rvMovie.layoutManager = context?.let { setGridPixel(it) }?.let { GridLayoutManager(context, it) }
        binding.rvMovie.adapter = movieAdapter
        binding.rvMovie.setHasFixedSize(true)

    }

    private val movieObserver = Observer<Resource<PagedList<MovieEntity>>> { movies ->
        if (movies != null){
            when (movies.status){
                Status.LOADING -> showProgressBar(true)
                Status.SUCCESS -> {
                    showProgressBar(false)
                    movieAdapter.submitList(movies.data)
                    movieAdapter.setOnItemClickCallback(this)
                    movieAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    showProgressBar(false)
                    setVisible(binding.ivNodata)
                    Toast.makeText(context, resources.getString(R.string.there_is_no_data_laoded), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showProgressBar(state: Boolean){
        binding.pbMovie.isVisible = state
        binding.rvMovie.isInvisible = state
    }

    override fun onItemClicked(data: MovieEntity) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(ID_MOVIE, data.id.toInt())
        context?.startActivity(intent)
    }

}