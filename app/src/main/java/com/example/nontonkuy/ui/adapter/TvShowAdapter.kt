package com.example.nontonkuy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nontonkuy.BuildConfig
import com.example.nontonkuy.data.source.local.entity.TvShowEntity
import com.example.nontonkuy.databinding.ItemGridBinding

class TvShowAdapter : PagedListAdapter<TvShowEntity, TvShowAdapter.ViewHolder> (DIFF_CALLBACK){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TvShowEntity)
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>(){
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(
            private val binding: ItemGridBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bindItem(item: TvShowEntity){
            with(binding){
                tvItemgridTitle.text = item.originalName
                tvItemgridRate.text = item.voteAverage.toString()
                tvItemgridDate.text = item.firstAirDate
                Glide.with(root.context)
                        .load("${BuildConfig.PATH_IMG}${item.posterPath}")
                        .into(ivItemgrid)

                root.setOnClickListener { onItemClickCallback.onItemClicked(item) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowAdapter.ViewHolder {
        val binding = ItemGridBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowAdapter.ViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null)
            holder.bindItem(tvShow)
    }

}