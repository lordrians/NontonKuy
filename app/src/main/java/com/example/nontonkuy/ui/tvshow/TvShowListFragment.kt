package com.example.nontonkuy.ui.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nontonkuy.R
import com.example.nontonkuy.data.source.remote.response.ResultsItemListTvShow
import com.example.nontonkuy.databinding.FragmentTvShowListBinding
import com.example.nontonkuy.ui.adapter.TvShowListAdapter
import com.example.nontonkuy.ui.tvshow.detail.TvShowDetailActivity
import com.example.nontonkuy.ui.tvshow.detail.TvShowDetailActivity.Companion.ID_TVSHOW
import com.example.nontonkuy.utils.TvShowViewModelFactory
import com.example.nontonkuy.utils.setGone
import com.example.nontonkuy.utils.setGridPixel
import com.example.nontonkuy.utils.setVisible

class TvShowListFragment : Fragment() {

    private lateinit var binding: FragmentTvShowListBinding
    private lateinit var adapter: TvShowListAdapter

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
        val factory = TvShowViewModelFactory.getInstance(requireActivity())
        val viewModels = ViewModelProvider(this, factory)[TvShowListViewModel::class.java]

        viewModels.getTvShows().observe(viewLifecycleOwner){ listTvShow ->
            if (listTvShow.size > 0){
                adapter = TvShowListAdapter(listTvShow)
                adapter.notifyDataSetChanged()
                binding.rvTvshow.layoutManager = context?.let { setGridPixel(it) }?.let { GridLayoutManager(context, it) }
                binding.rvTvshow.adapter = adapter

                adapter.setOnItemClickCallback(object : TvShowListAdapter.OnItemClickCallback{
                    override fun onItemClicked(data: ResultsItemListTvShow) {
                        val intent = Intent(context, TvShowDetailActivity::class.java)
                        intent.putExtra(ID_TVSHOW, data.id)
                        startActivity(intent)
                    }
                })

                setVisible(binding.rvTvshow)
                setGone(binding.pbTvshow)
                setGone(binding.ivNodata)

            } else {
                setVisible(binding.ivNodata)
                setGone(binding.pbTvshow)
                Toast.makeText(context,"onFailure:" + resources.getString(R.string.there_is_no_data_laoded), Toast.LENGTH_SHORT).show()
            }
        }
    }
}