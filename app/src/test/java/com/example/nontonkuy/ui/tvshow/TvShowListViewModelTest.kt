package com.example.nontonkuy.ui.tvshow

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.nontonkuy.data.source.local.entity.TvShowEntity
import com.example.nontonkuy.data.source.remote.response.ResponseListTvShow
import com.example.nontonkuy.data.source.repository.TvShowRepository
import com.example.nontonkuy.retrofit.ApiConfig
import com.example.nontonkuy.ui.tvshow.main.TvShowListViewModel
import com.example.nontonkuy.utils.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class TvShowListViewModelTest {

    private lateinit var viewModel: TvShowListViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedLIst: PagedList<TvShowEntity>

    @Before
    fun setUp(){
        viewModel = TvShowListViewModel(tvShowRepository)
    }

    @Test
    fun getTvShowList(){

        val dummyTvShow = Resource.success(pagedLIst)
        `when`(dummyTvShow.data?.size).thenReturn(3)

        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShow

        `when`(tvShowRepository.getListTvShow()).thenReturn(tvShows)
        val tvShow = viewModel.getTvShows().value?.data
        verify(tvShowRepository).getListTvShow()
        assertNotNull(tvShow)
        assertEquals(3, tvShow?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)

    }

}