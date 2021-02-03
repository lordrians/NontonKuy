package com.example.nontonkuy.ui.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nontonkuy.data.ResultsItemListMovie
import com.example.nontonkuy.databinding.FragmentMovieListBinding
import com.example.nontonkuy.ui.adapter.MovieListAdapter
import com.example.nontonkuy.ui.movie.detail.MovieDetailActivity
import com.example.nontonkuy.ui.movie.detail.MovieDetailActivity.Companion.ID_MOVIE
import com.example.nontonkuy.utils.EspressoIdlingResource
import com.example.nontonkuy.utils.setGone
import com.example.nontonkuy.utils.setGridPixel
import com.example.nontonkuy.utils.setVisible

class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var viewModel: MovieListViewModel
    private lateinit var adapter: MovieListAdapter

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

        viewModel = MovieListViewModel()


        viewModel.setListMovie()
        viewModel.getListMovie().observe(viewLifecycleOwner, Observer { listMovie ->
            if (listMovie.size > 0){

                adapter = MovieListAdapter(listMovie)
                adapter.notifyDataSetChanged()
                binding.rvMovie.setHasFixedSize(true)
                binding.rvMovie.layoutManager = context?.let { setGridPixel(it) }?.let { GridLayoutManager(context, it) }
                binding.rvMovie.adapter = adapter

                adapter.setOnItemClickCallback(object : MovieListAdapter.OnItemClickCallback{
                    override fun onItemClicked(data: ResultsItemListMovie) {
                        val intent = Intent(context, MovieDetailActivity::class.java)
                        intent.putExtra(ID_MOVIE, data.id)
                        startActivity(intent)
                    }
                })

                setVisible(binding.rvMovie)
                setGone(binding.pbMovie)
            } else {
                setVisible(binding.ivNodata)
                setGone(binding.pbMovie)
                Log.d("MovieListFragment","onFailure: Data tidak ada")
            }
        })

    }

}