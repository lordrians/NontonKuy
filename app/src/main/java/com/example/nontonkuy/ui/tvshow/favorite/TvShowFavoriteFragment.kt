package com.example.nontonkuy.ui.tvshow.favorite

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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nontonkuy.R
import com.example.nontonkuy.data.source.local.entity.TvShowEntity
import com.example.nontonkuy.databinding.FragmentTvShowFavoriteBinding
import com.example.nontonkuy.ui.adapter.TvShowAdapter
import com.example.nontonkuy.ui.movie.detail.MovieDetailActivity
import com.example.nontonkuy.ui.tvshow.TvShowViewModelFactory
import com.example.nontonkuy.ui.tvshow.detail.TvShowDetailActivity
import com.example.nontonkuy.ui.tvshow.detail.TvShowDetailActivity.Companion.ID_TVSHOW
import com.example.nontonkuy.utils.setGridPixel

class TvShowFavoriteFragment : Fragment(), TvShowAdapter.OnItemClickCallback {

    private lateinit var binding: FragmentTvShowFavoriteBinding
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTvShowFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()

    }

    private fun loadData() {
        val factory = TvShowViewModelFactory.getInstance(requireContext())
        val viewModel = ViewModelProvider(this, factory)[TvShowFavoriteViewModel::class.java]

        tvShowAdapter = TvShowAdapter()
        viewModel.getFavoriteTvShow().observe(viewLifecycleOwner, Observer { listFavorite ->
            if (listFavorite != null){
                showProgressBar(false)
                tvShowAdapter.submitList(listFavorite)
                tvShowAdapter.setOnItemClickCallback(this)
                tvShowAdapter.notifyDataSetChanged()
            } else
                Toast.makeText(context, resources.getString(R.string.there_is_no_data_laoded )+ " in favorite", Toast.LENGTH_SHORT).show()
        })

        binding.rvFavTvshow.layoutManager = context?.let { setGridPixel(it) }?.let { GridLayoutManager(context, it) }
        binding.rvFavTvshow.adapter = tvShowAdapter
        binding.rvFavTvshow.setHasFixedSize(true)

    }

    private fun showProgressBar(state: Boolean) {
        binding.pbFavTvshow.isVisible = state
        binding.rvFavTvshow.isInvisible = state
    }

    override fun onItemClicked(data: TvShowEntity) {
        val intent = Intent(context, TvShowDetailActivity::class.java)
        intent.putExtra(ID_TVSHOW, data.id)
        context?.startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }
}