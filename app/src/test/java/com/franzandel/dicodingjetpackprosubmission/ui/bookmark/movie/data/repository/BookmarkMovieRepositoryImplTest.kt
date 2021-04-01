package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.SortChoice
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.local.BookmarkMovieLocalDataSource
import com.franzandel.dicodingjetpackprosubmission.utils.PagedListUtil
import com.franzandel.dicodingjetpackprosubmission.utils.RoomUtils
import com.franzandel.dicodingjetpackprosubmission.utils.RoomUtils.ADD_FAILED_RESPONSE
import com.franzandel.dicodingjetpackprosubmission.utils.RoomUtils.ADD_SUCCESS_RESPONSE
import com.franzandel.dicodingjetpackprosubmission.utils.RoomUtils.DELETE_FAILED_RESPONSE
import com.franzandel.dicodingjetpackprosubmission.utils.RoomUtils.DELETE_SUCCESS_RESPONSE
import com.franzandel.dicodingjetpackprosubmission.utils.RoomUtils.MOVIE_ID
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
 * Created by Franz Andel on 31/03/21.
 * Android Engineer
 */

@ExperimentalCoroutinesApi
class BookmarkMovieRepositoryImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val observer: Observer<PagedList<BookmarkMovieResponse>> = mockk(relaxed = true)
    private val localDataSource: BookmarkMovieLocalDataSource = mockk(relaxed = true)

    private lateinit var repository: BookmarkMovieRepository

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        repository = BookmarkMovieRepositoryImpl(localDataSource)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `get bookmark movies`() {
        runBlocking {
            val sortChoice = SortChoice.TITLE
            val dummyBookmarkMovies = RoomUtils.getBookmarkMoviesResponse()
            val bookmarkMoviesLiveData = MutableLiveData<PagedList<BookmarkMovieResponse>>()
            bookmarkMoviesLiveData.value = PagedListUtil.mockPagedList(dummyBookmarkMovies)

            coEvery { localDataSource.getAll(sortChoice) } returns bookmarkMoviesLiveData

            val moviesResource = repository.getAll(sortChoice)
            moviesResource.observeForever(observer)
            val movies = moviesResource.value

            verify {
                observer.onChanged(movies)
                assertNotNull(movies)
                assertEquals(dummyBookmarkMovies.size, movies?.size)
            }
        }
    }

    @Test
    fun `get empty bookmark movies`() {
        runBlocking {
            val sortChoice = SortChoice.TITLE
            val dummyBookmarkMovies = listOf<BookmarkMovieResponse>()
            val bookmarkMoviesLiveData = MutableLiveData<PagedList<BookmarkMovieResponse>>()
            bookmarkMoviesLiveData.value = PagedListUtil.mockPagedList(dummyBookmarkMovies)

            coEvery { localDataSource.getAll(sortChoice) } returns bookmarkMoviesLiveData

            val moviesResource = repository.getAll(sortChoice)
            moviesResource.observeForever(observer)
            val movies = moviesResource.value

            verify {
                observer.onChanged(movies)
                assertNotNull(movies)
                assertEquals(dummyBookmarkMovies.size, movies?.size)
            }
        }
    }

    @Test
    fun `get bookmark movie is not null`() {
        runBlocking {
            val bookmarkMovieResponse = RoomUtils.getBookmarkMovieResponse()
            coEvery { localDataSource.get(MOVIE_ID) } returns bookmarkMovieResponse
            val getResponse = repository.get(MOVIE_ID)
            assertEquals(getResponse, bookmarkMovieResponse)
        }
    }

    @Test
    fun `get bookmark movie is null`() {
        runBlocking {
            val bookmarkMovieResponse = null
            coEvery { localDataSource.get(MOVIE_ID) } returns bookmarkMovieResponse
            val getResponse = repository.get(MOVIE_ID)
            assertEquals(getResponse, bookmarkMovieResponse)
        }
    }

    @Test
    fun `add bookmark movie success`() {
        runBlocking {
            val bookmarkMovieRequest = RoomUtils.getBookmarkMovieRequest()
            coEvery { localDataSource.add(bookmarkMovieRequest) } returns ADD_SUCCESS_RESPONSE
            val addResponse = repository.add(bookmarkMovieRequest)
            assertEquals(addResponse, ADD_SUCCESS_RESPONSE)
        }
    }

    @Test
    fun `add bookmark movie error`() {
        runBlocking {
            val bookmarkMovieRequest = RoomUtils.getBookmarkMovieRequest()
            coEvery { localDataSource.add(bookmarkMovieRequest) } returns ADD_FAILED_RESPONSE
            val addResponse = repository.add(bookmarkMovieRequest)
            assertEquals(addResponse, ADD_FAILED_RESPONSE)
        }
    }

    @Test
    fun `delete bookmark movie success`() {
        runBlocking {
            coEvery { localDataSource.delete(MOVIE_ID) } returns DELETE_SUCCESS_RESPONSE
            val deleteResponse = repository.delete(MOVIE_ID)
            assertEquals(deleteResponse, DELETE_SUCCESS_RESPONSE)
        }
    }

    @Test
    fun `delete bookmark movie error`() {
        runBlocking {
            coEvery { localDataSource.delete(MOVIE_ID) } returns DELETE_FAILED_RESPONSE
            val deleteResponse = repository.delete(MOVIE_ID)
            assertEquals(deleteResponse, DELETE_FAILED_RESPONSE)
        }
    }
}