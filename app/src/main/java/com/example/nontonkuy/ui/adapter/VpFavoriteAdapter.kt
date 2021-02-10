package com.example.nontonkuy.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nontonkuy.R
import com.example.nontonkuy.ui.movie.favorite.MovieFavoriteFragment
import com.example.nontonkuy.ui.tvshow.favorite.TvShowFavoriteFragment

class VpFavoriteAdapter (
    private val fm: FragmentActivity
): FragmentStateAdapter(fm){

    override fun getItemCount(): Int =
        fm.resources.getStringArray(R.array.TAB_TITLES_MAIN).size

    override fun createFragment(position: Int): Fragment {

        when(position){
            0 -> return MovieFavoriteFragment()
            1 -> return TvShowFavoriteFragment()
        }
        return MovieFavoriteFragment()
    }
}