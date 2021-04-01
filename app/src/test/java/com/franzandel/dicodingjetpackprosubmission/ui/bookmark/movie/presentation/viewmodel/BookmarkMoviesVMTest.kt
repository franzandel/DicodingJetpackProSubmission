package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.data.consts.RoomConsts
import com.franzandel.dicodingjetpackprosubmission.external.coroutine.CoroutineProvider
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.SortChoice
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.repository.BookmarkMovieRepository
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.mapper.BookmarkMovieResponseRequestMapper
import com.franzandel.dicodingjetpackprosubmission.utils.PagedListUtil.mockPagedList
import com.franzandel.dicodingjetpackprosubmission.utils.RoomUtils
import com.franzandel.dicodingjetpackprosubmission.utils.RoomUtils.ADD_FAILED_RESPONSE
import com.franzandel.dicodingjetpackprosubmission.utils.RoomUtils.ADD_SUCCESS_RESPONSE
import com.franzandel.dicodingjetpackprosubmission.utils.RoomUtils.DELETE_FAILED_RESPONSE
import com.franzandel.dicodingjetpackprosubmission.utils.RoomUtils.DELETE_SUCCESS_RESPONSE
import com.franzandel.dicodingjetpackprosubmission.utils.RoomUtils.MOVIE_ID
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Franz Andel on 30/03/21.
 * Android Engineer
 */

@ExperimentalCoroutinesApi
class BookmarkMoviesVMTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val bookmarkMovieRepository: BookmarkMovieRepository = mockk(relaxed = true)
    private val coroutineProvider: CoroutineProvider = mockk(relaxed = true)
    private val getAllObserver: Observer<PagedList<BookmarkMovieResponse>> = mockk(relaxed = true)
    private val successObserver: Observer<Unit> = mockk(relaxed = true)
    private val deleteFailedObserver: Observer<String> = mockk(relaxed = true)

    private lateinit var viewModel: BookmarkMoviesVM

    private val mapper: BaseMapper<BookmarkMovieResponse, BookmarkMovieRequest> =
        BookmarkMovieResponseRequestMapper()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        viewModel = BookmarkMoviesVM(bookmarkMovieRepository, coroutineProvider, mapper)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `get bookmark movies success`() {
        runBlockingTest {
            val sortChoice = SortChoice.TITLE
            val dummyBookmarkMovies = RoomUtils.getBookmarkMoviesResponse()
            val bookmarkMoviesLiveData = MutableLiveData<PagedList<BookmarkMovieResponse>>()
            bookmarkMoviesLiveData.value = mockPagedList(dummyBookmarkMovies)

            coEvery { bookmarkMovieRepository.getAll(sortChoice) } returns bookmarkMoviesLiveData
            coEvery { coroutineProvider.main() } returns Dispatchers.Unconfined
            coEvery { coroutineProvider.background() } returns Dispatchers.Unconfined

            viewModel.bookmarkMovies.observeForever(getAllObserver)
            viewModel.getBookmarkMovies(sortChoice)

            verify {
                val bookmarkMovies = viewModel.bookmarkMovies.value
                getAllObserver.onChanged(bookmarkMovies)
                assertNotNull(bookmarkMovies)
                Assert.assertEquals(dummyBookmarkMovies.size, bookmarkMovies?.size)
            }
        }
    }

    @Test
    fun `get empty bookmark movies`() {
        runBlockingTest {
            val sortChoice = SortChoice.TITLE
            val emptyBookmarkMovies = emptyList<BookmarkMovieResponse>()
            val bookmarkMoviesLiveData = MutableLiveData<PagedList<BookmarkMovieResponse>>()
            bookmarkMoviesLiveData.value = mockPagedList(emptyBookmarkMovies)

            coEvery { bookmarkMovieRepository.getAll(sortChoice) } returns bookmarkMoviesLiveData
            coEvery { coroutineProvider.main() } returns Dispatchers.Unconfined
            coEvery { coroutineProvider.background() } returns Dispatchers.Unconfined

            viewModel.bookmarkMovies.observeForever(getAllObserver)
            viewModel.getBookmarkMovies(sortChoice)

            verify {
                val bookmarkMovies = viewModel.bookmarkMovies.value
                getAllObserver.onChanged(bookmarkMovies)
                assertNotNull(bookmarkMovies)
                Assert.assertEquals(emptyBookmarkMovies.size, bookmarkMovies?.size)
            }
        }
    }

    @Test
    fun `delete bookmark movie success`() {
        runBlockingTest {
            coEvery { bookmarkMovieRepository.delete(MOVIE_ID) } returns DELETE_SUCCESS_RESPONSE
            coEvery { coroutineProvider.background() } returns Dispatchers.Unconfined

            viewModel.deleteBookmarkResult.observeForever(successObserver)
            viewModel.deleteMovieFromBookmark(MOVIE_ID)

            verify {
                val deleteBookmarkResult = viewModel.deleteBookmarkResult.value
                successObserver.onChanged(deleteBookmarkResult)
                assertNotNull(deleteBookmarkResult)
            }
        }
    }

    @Test
    fun `delete bookmark movie error`() {
        runBlockingTest {
            coEvery { bookmarkMovieRepository.delete(MOVIE_ID) } returns DELETE_FAILED_RESPONSE
            coEvery { coroutineProvider.background() } returns Dispatchers.Unconfined

            viewModel.errorResult.observeForever(deleteFailedObserver)
            viewModel.deleteMovieFromBookmark(MOVIE_ID)

            verify {
                val errorMessage = viewModel.errorResult.value
                deleteFailedObserver.onChanged(errorMessage)
                assertNotNull(errorMessage)
                Assert.assertEquals(RoomConsts.REMOVE_BOOKMARK_FAILED, errorMessage)
            }
        }
    }

    @Test
    fun `add bookmark movie success`() {
        runBlockingTest {
            val bookmarkMovieResponse = RoomUtils.getBookmarkMovieResponse()
            val bookmarkMovieRequest = mapper.map(bookmarkMovieResponse)

            coEvery { bookmarkMovieRepository.add(bookmarkMovieRequest) } returns ADD_SUCCESS_RESPONSE
            coEvery { coroutineProvider.background() } returns Dispatchers.Unconfined

            viewModel.addBookmarkResult.observeForever(successObserver)
            viewModel.addMovieToBookmark(bookmarkMovieResponse)

            verify {
                val addBookmarkResult = viewModel.addBookmarkResult.value
                successObserver.onChanged(addBookmarkResult)
                assertNotNull(addBookmarkResult)
            }
        }
    }

    @Test
    fun `add bookmark movie error`() {
        runBlockingTest {
            val bookmarkMovieResponse = RoomUtils.getBookmarkMovieResponse()
            val bookmarkMovieRequest = mapper.map(bookmarkMovieResponse)

            coEvery { bookmarkMovieRepository.add(bookmarkMovieRequest) } returns ADD_FAILED_RESPONSE
            coEvery { coroutineProvider.background() } returns Dispatchers.Unconfined

            viewModel.errorResult.observeForever(deleteFailedObserver)
            viewModel.addMovieToBookmark(bookmarkMovieResponse)

            verify {
                val errorMessage = viewModel.errorResult.value
                deleteFailedObserver.onChanged(errorMessage)
                assertNotNull(errorMessage)
                Assert.assertEquals(RoomConsts.ADD_BOOKMARK_FAILED, errorMessage)
            }
        }
    }
}

