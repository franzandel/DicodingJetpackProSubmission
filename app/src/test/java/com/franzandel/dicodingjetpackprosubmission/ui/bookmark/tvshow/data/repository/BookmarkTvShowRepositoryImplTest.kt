package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.SortChoice
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.local.BookmarkTvShowLocalDataSource
import com.franzandel.dicodingjetpackprosubmission.utils.PagedListUtil
import com.franzandel.dicodingjetpackprosubmission.utils.RoomUtils
import com.franzandel.dicodingjetpackprosubmission.utils.RoomUtils.ADD_FAILED_RESPONSE
import com.franzandel.dicodingjetpackprosubmission.utils.RoomUtils.ADD_SUCCESS_RESPONSE
import com.franzandel.dicodingjetpackprosubmission.utils.RoomUtils.DELETE_FAILED_RESPONSE
import com.franzandel.dicodingjetpackprosubmission.utils.RoomUtils.DELETE_SUCCESS_RESPONSE
import com.franzandel.dicodingjetpackprosubmission.utils.RoomUtils.TV_SHOW_ID
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Franz Andel on 01/04/21.
 * Android Engineer
 */

@ExperimentalCoroutinesApi
class BookmarkTvShowRepositoryImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val observer: Observer<PagedList<BookmarkTvShowResponse>> = mockk(relaxed = true)
    private val localDataSource: BookmarkTvShowLocalDataSource = mockk(relaxed = true)

    private lateinit var repository: BookmarkTvShowRepository

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        repository = BookmarkTvShowRepositoryImpl(localDataSource)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `get bookmark tv shows`() {
        runBlocking {
            val sortChoice = SortChoice.TITLE
            val dummyBookmarkTvShows = RoomUtils.getBookmarkTvShowsResponse()
            val bookmarkTvShowsLiveData = MutableLiveData<PagedList<BookmarkTvShowResponse>>()
            bookmarkTvShowsLiveData.value = PagedListUtil.mockPagedList(dummyBookmarkTvShows)

            coEvery { localDataSource.getAll(sortChoice) } returns bookmarkTvShowsLiveData

            val tvShowsResource = repository.getAll(sortChoice)
            tvShowsResource.observeForever(observer)
            val tvShows = tvShowsResource.value

            verify {
                observer.onChanged(tvShows)
                assertNotNull(tvShows)
                assertEquals(dummyBookmarkTvShows.size, tvShows?.size)
            }
        }
    }

    @Test
    fun `get empty bookmark tv shows`() {
        runBlocking {
            val sortChoice = SortChoice.TITLE
            val dummyBookmarkTvShows = listOf<BookmarkTvShowResponse>()
            val bookmarkTvShowsLiveData = MutableLiveData<PagedList<BookmarkTvShowResponse>>()
            bookmarkTvShowsLiveData.value = PagedListUtil.mockPagedList(dummyBookmarkTvShows)

            coEvery { localDataSource.getAll(sortChoice) } returns bookmarkTvShowsLiveData

            val tvShowsResource = repository.getAll(sortChoice)
            tvShowsResource.observeForever(observer)
            val tvShows = tvShowsResource.value

            verify {
                observer.onChanged(tvShows)
                assertNotNull(tvShows)
                assertEquals(dummyBookmarkTvShows.size, tvShows?.size)
            }
        }
    }

    @Test
    fun `get bookmark tv show is not null`() {
        runBlocking {
            val bookmarkTvShowResponse = RoomUtils.getBookmarkTvShowResponse()
            coEvery { localDataSource.get(TV_SHOW_ID) } returns bookmarkTvShowResponse
            val getResponse = repository.get(TV_SHOW_ID)
            assertEquals(getResponse, bookmarkTvShowResponse)
        }
    }

    @Test
    fun `get bookmark tv show is null`() {
        runBlocking {
            val bookmarkTvShowResponse = null
            coEvery { localDataSource.get(TV_SHOW_ID) } returns bookmarkTvShowResponse
            val getResponse = repository.get(TV_SHOW_ID)
            assertEquals(getResponse, bookmarkTvShowResponse)
        }
    }

    @Test
    fun `add bookmark tv show success`() {
        runBlocking {
            val bookmarkTvShowRequest = RoomUtils.getBookmarkTvShowRequest()
            coEvery { localDataSource.add(bookmarkTvShowRequest) } returns ADD_SUCCESS_RESPONSE
            val addResponse = repository.add(bookmarkTvShowRequest)
            assertEquals(addResponse, ADD_SUCCESS_RESPONSE)
        }
    }

    @Test
    fun `add bookmark tv show error`() {
        runBlocking {
            val bookmarkTvShowRequest = RoomUtils.getBookmarkTvShowRequest()
            coEvery { localDataSource.add(bookmarkTvShowRequest) } returns ADD_FAILED_RESPONSE
            val addResponse = repository.add(bookmarkTvShowRequest)
            assertEquals(addResponse, ADD_FAILED_RESPONSE)
        }
    }

    @Test
    fun `delete bookmark tv show success`() {
        runBlocking {
            coEvery { localDataSource.delete(TV_SHOW_ID) } returns DELETE_SUCCESS_RESPONSE
            val deleteResponse = repository.delete(TV_SHOW_ID)
            assertEquals(deleteResponse, DELETE_SUCCESS_RESPONSE)
        }
    }

    @Test
    fun `delete bookmark tv show error`() {
        runBlocking {
            coEvery { localDataSource.delete(TV_SHOW_ID) } returns DELETE_FAILED_RESPONSE
            val deleteResponse = repository.delete(TV_SHOW_ID)
            assertEquals(deleteResponse, DELETE_FAILED_RESPONSE)
        }
    }
}