package com.example.nontonkuy.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.data.source.repository.MovieRepository
import com.example.nontonkuy.ui.movie.favorite.MovieFavoriteViewModel
import com.example.nontonkuy.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest {
    private lateinit var viewModel: MovieFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieFavoriteViewModel(movieCatalogueRepository)
    }

    @Test
    fun getFavMovies() {
        val dummyMovie = pagedList
        Mockito.`when`(dummyMovie.size).thenReturn(3)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovie

        Mockito.`when`(movieCatalogueRepository.getFavMovie()).thenReturn(movies)
        val movie = viewModel.getFavoriteMovie().value
        verify(movieCatalogueRepository).getFavMovie()
        Assert.assertNotNull(movie)
        Assert.assertEquals(3, movie?.size)

        viewModel.getFavoriteMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun setFavMovie() {
        viewModel.setFavMovie(DataDummy.getDetailMovie())
        verify(movieCatalogueRepository).setFavMovie(DataDummy.getDetailMovie(), true)
        verifyNoMoreInteractions(movieCatalogueRepository)
    }
}