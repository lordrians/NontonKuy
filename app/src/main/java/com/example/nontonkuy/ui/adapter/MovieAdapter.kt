package com.example.nontonkuy.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nontonkuy.BuildConfig
import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.data.source.remote.response.ResultsItemListMovie
import com.example.nontonkuy.databinding.ItemGridBinding

class MovieAdapter : PagedListAdapter<MovieEntity, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: MovieEntity)
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null)
            holder.bindItem(movie)
    }

    inner class ViewHolder (
            private val binding: ItemGridBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item : MovieEntity) {
            with(binding){
                tvItemgridDate.text = item.releaseDate
                tvItemgridRate.text = item.voteAverage.toString()
                tvItemgridTitle.text = item.originalTitle
                Glide.with(root.context)
                        .load("${BuildConfig.PATH_IMG}${item.posterPath}")
                        .into(ivItemgrid)

                root.setOnClickListener { onItemClickCallback.onItemClicked(item) }

            }
        }
    }

}