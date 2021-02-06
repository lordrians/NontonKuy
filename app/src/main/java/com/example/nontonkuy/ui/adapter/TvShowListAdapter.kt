package com.example.nontonkuy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nontonkuy.BuildConfig
import com.example.nontonkuy.data.source.remote.response.ResultsItemListTvShow
import com.example.nontonkuy.databinding.ItemGridBinding

class TvShowListAdapter(
    private val listTvShow: List<ResultsItemListTvShow>
) : RecyclerView.Adapter<TvShowListAdapter.ViewHolder>(){

    private var onItemClickCallback: OnItemClickCallback? = null

    inner class ViewHolder (
        private val binding: ItemGridBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bindItem(item: ResultsItemListTvShow){
            with(binding){
                tvItemgridTitle.text = item.name
                tvItemgridRate.text = item.voteAverage.toString()
                tvItemgridDate.text = item.firstAirDate

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

    override fun getItemCount(): Int = listTvShow.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listTvShow[position])
    }


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ResultsItemListTvShow)
    }
}