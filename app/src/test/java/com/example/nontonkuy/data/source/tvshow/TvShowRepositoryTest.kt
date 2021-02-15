package com.example.nontonkuy.data.source.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.nontonkuy.data.source.local.TvShowLocalDataSource
import com.example.nontonkuy.data.source.local.entity.TvShowEntity
import com.example.nontonkuy.data.source.remote.tvshow.TvShowRemoteDataSource
import com.example.nontonkuy.utils.*
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class TvShowRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(TvShowRemoteDataSource::class.java)
    private val local = mock(TvShowLocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val tvShowRepository = FakeTvShowRepository(remote, local, appExecutors)

    private val tvShowResponse = DataDummy.getRemoteTvShows()
    private val tvShowId = tvShowResponse[0].id
    private val tvShowDetail = DataDummy.getRemoteDetailTvShow()


    @Test
    fun getTvShow(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getListTvShow()).thenReturn(dataSourceFactory)
        tvShowRepository.getListTvShow()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getTvShows()))
        verify(local).getListTvShow()
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(tvShowResponse.size, tvShowEntities.data?.size)
    }

    @Test
    fun getDetailTvShow() {
        val dummyDetail = MutableLiveData<TvShowEntity>()
        dummyDetail.value = DataDummy.getDetailTvShow()
        `when`(local.getDetailTvShow(tvShowId.toString())).thenReturn(dummyDetail)

        val tvDetailTvShowEntities = LiveDataTestUtil.getValue(tvShowRepository.getDetailTvShows(tvShowId.toString()))
        verify(local).getDetailTvShow(tvShowId.toString())
        Assert.assertNotNull(tvDetailTvShowEntities)
        Assert.assertEquals(tvShowDetail.id, tvDetailTvShowEntities.data?.id)
    }

    @Test
    fun getFavoriteTvShow(){

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getListFavTvShow()).thenReturn(dataSourceFactory)
        tvShowRepository.getFavTvShow()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getTvShows()))
        verify(local).getListFavTvShow()
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(tvShowResponse.size, tvShowEntities.data?.size)

    }

    @Test
    fun setFavoriteTvShow() {
        tvShowRepository.setFavTvShow(DataDummy.getDetailTvShow(), true)
        verify(local).setFavTvShow(DataDummy.getDetailTvShow(), true)
        verifyNoMoreInteractions(local)
    }
}