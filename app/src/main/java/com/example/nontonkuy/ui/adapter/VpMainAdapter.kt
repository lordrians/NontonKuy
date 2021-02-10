package com.example.nontonkuy.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nontonkuy.R
import com.example.nontonkuy.ui.movie.main.MovieListFragment
import com.example.nontonkuy.ui.tvshow.main.TvShowListFragment

class VpMainAdapter (
    private val fm: FragmentActivity
): FragmentStateAdapter(fm){
    override fun getItemCount(): Int = fm.resources.getStringArray(R.array.TAB_TITLES_MAIN).size

    override fun createFragment(position: Int): Fragment {
        when (position){
            0 -> return MovieListFragment()
            1 -> return TvShowListFragment()
        }
        return MovieListFragment()
    }

}