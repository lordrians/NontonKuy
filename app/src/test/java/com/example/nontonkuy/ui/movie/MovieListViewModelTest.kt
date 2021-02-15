package com.example.nontonkuy.ui.movie

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.data.source.remote.response.ResponseListMovie
import com.example.nontonkuy.data.source.repository.MovieRepository
import com.example.nontonkuy.retrofit.ApiConfig
import com.example.nontonkuy.ui.movie.main.MovieListViewModel
import com.example.nontonkuy.utils.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class MovieListViewModelTest{


    private lateinit var viewModel: MovieListViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieListViewModel(movieRepository)
    }



    @Test
    fun getMovieList(){

        val dummyMovies = Resource.success(pagedList)
        Mockito.`when`(dummyMovies.data?.size).thenReturn(3)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        Mockito.`when`(movieRepository.getListMovie()).thenReturn(movies)
        val movie = viewModel.getMovies().value?.data
        verify(movieRepository).getListMovie()
        assertNotNull(movie)
        assertEquals(3, movie?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)


    }

}