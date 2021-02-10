package com.example.nontonkuy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.nontonkuy.R
import com.example.nontonkuy.databinding.ActivityFavoriteBinding
import com.example.nontonkuy.ui.adapter.VpFavoriteAdapter
import com.example.nontonkuy.ui.adapter.VpMainAdapter
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.tbFavorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.tbFavorite.setTitle(R.string.title_favorite)
//        actionBar?.setDisplayShowHomeEnabled(true)

        settingViewPager()

    }

    private fun settingViewPager() {
        val tab_titles = resources.getStringArray(R.array.TAB_TITLES_MAIN)
        val adapter = VpFavoriteAdapter(this)
        binding.vpFavorite.adapter = adapter

        TabLayoutMediator(binding.tlFavorite, binding.vpFavorite){tab, position ->
            tab.text = tab_titles.get(position)
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> { onBackPressed() }
        }
        return super.onOptionsItemSelected(item)
    }
}