package com.example.nontonkuy.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.nontonkuy.data.source.local.entity.TvShowEntity
import com.example.nontonkuy.data.source.repository.TvShowRepository
import com.example.nontonkuy.ui.tvshow.favorite.TvShowFavoriteViewModel
import com.example.nontonkuy.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowViewModelTest {

    private lateinit var viewModel: TvShowFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedLIst: PagedList<TvShowEntity>

    @Before
    fun setUp(){
        viewModel = TvShowFavoriteViewModel(tvShowRepository)
    }

    @Test
    fun getFavMovies(){
        val dummyTvShow = pagedLIst
        `when`(dummyTvShow.size).thenReturn(3)

        val tvShows = MutableLiveData<PagedList<TvShowEntity>>()
        tvShows.value = dummyTvShow

        `when`(tvShowRepository.getFavTvShow()).thenReturn(tvShows)
        val tvShow = viewModel.getFavoriteTvShow().value
        verify(tvShowRepository).getFavTvShow()
        assertNotNull(tvShow)
        assertEquals(3, tvShow?.size)

        viewModel.getFavoriteTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }

    @Test
    fun setFavMovie() {
        viewModel.setFavTvShow(DataDummy.getDetailTvShow())
        verify(tvShowRepository).setFavTvShow(DataDummy.getDetailTvShow(), true)
        verifyNoMoreInteractions(tvShowRepository)
    }

}