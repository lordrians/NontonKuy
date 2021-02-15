package com.example.nontonkuy.data.source.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.nontonkuy.data.source.local.MovieLocalDataSource
import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.data.source.remote.movie.MovieRemoteDataSource
import com.example.nontonkuy.utils.*
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(MovieRemoteDataSource::class.java)
    private val local = mock(MovieLocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    private val moviesResponse = DataDummy.getRemoteMovies()
    private val movieId = moviesResponse[0].id
    private val movieDetail = DataDummy.getRemoteDetailMovie()


    @Test
    fun getMovies(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getListMovie()).thenReturn(dataSourceFactory)
        movieRepository.getListMovie()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovies()))
        verify(local).getListMovie()
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.data?.size)
    }

    @Test
    fun getDetailMovie() {
        val dummyDetail = MutableLiveData<MovieEntity>()
        dummyDetail.value = DataDummy.getDetailMovie()
        `when`(local.getDetailMovie(movieId.toString())).thenReturn(dummyDetail)

        val movieDetailEntity = LiveDataTestUtil.getValue(movieRepository.getDetailMovies(movieId.toString()))
        verify(local).getDetailMovie(movieId.toString())
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id, movieDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteMovie(){

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getListFavMovie()).thenReturn(dataSourceFactory)
        movieRepository.getFavMovie()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovies()))
        verify(local).getListFavMovie()
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size, movieEntities.data?.size)

    }

    @Test
    fun setFavoriteMovie() {
        movieRepository.setFavMovie(DataDummy.getDetailMovie(), true)
        verify(local).setFavMovie(DataDummy.getDetailMovie(), true)
        verifyNoMoreInteractions(local)
    }

}