package com.example.nontonkuy.ui.tvshow.main

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
import com.example.nontonkuy.databinding.FragmentTvShowListBinding
import com.example.nontonkuy.ui.adapter.TvShowAdapter
import com.example.nontonkuy.ui.tvshow.TvShowViewModelFactory
import com.example.nontonkuy.ui.tvshow.detail.TvShowDetailActivity
import com.example.nontonkuy.ui.tvshow.detail.TvShowDetailActivity.Companion.ID_TVSHOW
import com.example.nontonkuy.utils.*

class TvShowListFragment : Fragment() , TvShowAdapter.OnItemClickCallback{

    private lateinit var binding: FragmentTvShowListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTvShowListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()

    }

    private fun loadData() {
        val factory = TvShowViewModelFactory.getInstance(requireContext())
        val viewModels = ViewModelProvider(this, factory)[TvShowListViewModel::class.java]

        val tvShowAdapter = TvShowAdapter()
        viewModels.getTvShows().observe(viewLifecycleOwner, Observer { tvShows ->
            if (tvShows != null){
                when (tvShows.status){
                    Status.LOADING -> showProgressBar(true)
                    Status.SUCCESS -> {
                        showProgressBar(false)
                        tvShowAdapter.submitList(tvShows.data)
                        tvShowAdapter.setOnItemClickCallback(this)
                        tvShowAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        showProgressBar(false)
                        setVisible(binding.ivNodata)
                        Toast.makeText(context, resources.getString(R.string.there_is_no_data_laoded), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        binding.rvTvshow.layoutManager = context?.let { setGridPixel(it) }?.let { GridLayoutManager(context, it) }
        binding.rvTvshow.adapter = tvShowAdapter
        binding.rvTvshow.setHasFixedSize(true)
    }

    private fun showProgressBar(state: Boolean){
        binding.pbTvshow.isVisible = state
        binding.rvTvshow.isInvisible = state
    }

    override fun onItemClicked(data: TvShowEntity) {
        val intent = Intent(context, TvShowDetailActivity::class.java)
        intent.putExtra(ID_TVSHOW, data.id)
        context?.startActivity(intent)
    }
}