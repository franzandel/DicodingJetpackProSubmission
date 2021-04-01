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

    companion object {
        private const val MOVIE_ID = 1
    }

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
            val bookmarkMovieDTO = null

            coEvery { localDataSource.get(MOVIE_ID) } returns bookmarkMovieDTO
            val getResponse = repository.get(MOVIE_ID)

            assertEquals(getResponse, bookmarkMovieDTO)
        }
    }

    @Test
    fun `add bookmark movie success`() {
        runBlocking {
            val bookmarkMovieRequest = RoomUtils.getBookmarkMovieRequest()

            coEvery { localDataSource.add(bookmarkMovieRequest) } returns 2
            val addResponse = repository.add(bookmarkMovieRequest)
            assertEquals(addResponse, 2)
        }
    }

    @Test
    fun `add bookmark movie error`() {
        runBlocking {
            val bookmarkMovieRequest = RoomUtils.getBookmarkMovieRequest()

            coEvery { localDataSource.add(bookmarkMovieRequest) } returns -1
            val addResponse = repository.add(bookmarkMovieRequest)
            assertEquals(addResponse, -1)
        }
    }

    @Test
    fun `delete bookmark movie success`() {
        runBlocking {
            coEvery { localDataSource.delete(MOVIE_ID) } returns 2
            val deleteResponse = repository.delete(MOVIE_ID)
            assertEquals(deleteResponse, 2)
        }
    }

    @Test
    fun `delete bookmark movie error`() {
        runBlocking {
            coEvery { localDataSource.delete(MOVIE_ID) } returns -1
            val deleteResponse = repository.delete(MOVIE_ID)
            assertEquals(deleteResponse, -1)
        }
    }
}