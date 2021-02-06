package com.example.nontonkuy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nontonkuy.BuildConfig
import com.example.nontonkuy.data.source.remote.response.ResultsItemListMovie
import com.example.nontonkuy.databinding.ItemGridBinding

class MovieListAdapter(
        private val listMovie: List<ResultsItemListMovie>
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>(){

    private var onItemClickCallback: OnItemClickCallback? = null

    inner class ViewHolder (
            private val binding: ItemGridBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bindItem(item: ResultsItemListMovie){
            with(binding){
                tvItemgridDate.text = item.releaseDate
                tvItemgridRate.text = item.voteAverage.toString()
                tvItemgridTitle.text = item.title
                Glide.with(root.context)
                        .load("${BuildConfig.PATH_IMG}${item.posterPath}")
                        .into(ivItemgrid)

                root.setOnClickListener {
                    onItemClickCallback?.onItemClicked(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGridBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listMovie[position])
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ResultsItemListMovie)
    }
}