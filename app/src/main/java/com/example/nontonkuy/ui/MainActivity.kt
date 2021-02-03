package com.example.nontonkuy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nontonkuy.R
import com.example.nontonkuy.databinding.ActivityMainBinding
import com.example.nontonkuy.ui.adapter.VpMainAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingViewPager()

    }

    private fun settingViewPager() {
        val tab_titles = resources.getStringArray(R.array.TAB_TITLES_MAIN)
        val adapter = VpMainAdapter(this)
        binding.vpMain.adapter = adapter

        TabLayoutMediator(binding.tlMain, binding.vpMain){tab, position ->
            tab.text = tab_titles.get(position)
        }.attach()
    }
}