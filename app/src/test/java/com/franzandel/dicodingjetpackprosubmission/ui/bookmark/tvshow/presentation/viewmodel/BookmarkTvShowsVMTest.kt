package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.data.consts.RoomConsts
import com.franzandel.dicodingjetpackprosubmission.external.coroutine.CoroutineProvider
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.SortChoice
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.repository.BookmarkTvShowRepository
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.presentation.mapper.BookmarkTvShowResponseRequestMapper
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
 * Created by Franz Andel on 31/03/21.
 * Android Engineer
 */

@ExperimentalCoroutinesApi
class BookmarkTvShowsVMTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val bookmarkTvShowRepository: BookmarkTvShowRepository = mockk(relaxed = true)
    private val coroutineProvider: CoroutineProvider = mockk(relaxed = true)
    private val getAllObserver: Observer<PagedList<BookmarkTvShowResponse>> = mockk(relaxed = true)
    private val successObserver: Observer<Unit> = mockk(relaxed = true)
    private val deleteFailedObserver: Observer<String> = mockk(relaxed = true)

    private lateinit var viewModel: BookmarkTvShowsVM

    private val mapper: BaseMapper<BookmarkTvShowResponse, BookmarkTvShowRequest> =
        BookmarkTvShowResponseRequestMapper()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        viewModel = BookmarkTvShowsVM(bookmarkTvShowRepository, coroutineProvider, mapper)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `get bookmark tv shows success`() {
        runBlockingTest {
            val sortChoice = SortChoice.TITLE
            val dummyBookmarkTvShows = RoomUtils.getBookmarkTvShowsResponse()
            val bookmarkTvShowsLiveData = MutableLiveData<PagedList<BookmarkTvShowResponse>>()
            bookmarkTvShowsLiveData.value = PagedListUtil.mockPagedList(dummyBookmarkTvShows)

            coEvery { bookmarkTvShowRepository.getAll(sortChoice) } returns bookmarkTvShowsLiveData
            coEvery { coroutineProvider.main() } returns Dispatchers.Unconfined
            coEvery { coroutineProvider.background() } returns Dispatchers.Unconfined

            viewModel.bookmarkTvShows.observeForever(getAllObserver)
            viewModel.getBookmarkTvShows(sortChoice)

            verify {
                val bookmarkTvShows = viewModel.bookmarkTvShows.value
                getAllObserver.onChanged(bookmarkTvShows)
                assertNotNull(bookmarkTvShows)
                Assert.assertEquals(dummyBookmarkTvShows.size, bookmarkTvShows?.size)
            }
        }
    }

    @Test
    fun `get empty bookmark tv shows`() {
        runBlockingTest {
            val sortChoice = SortChoice.TITLE
            val emptyBookmarkTvShows = emptyList<BookmarkTvShowResponse>()
            val bookmarkTvShowsLiveData = MutableLiveData<PagedList<BookmarkTvShowResponse>>()
            bookmarkTvShowsLiveData.value = PagedListUtil.mockPagedList(emptyBookmarkTvShows)

            coEvery { bookmarkTvShowRepository.getAll(sortChoice) } returns bookmarkTvShowsLiveData
            coEvery { coroutineProvider.main() } returns Dispatchers.Unconfined
            coEvery { coroutineProvider.background() } returns Dispatchers.Unconfined

            viewModel.bookmarkTvShows.observeForever(getAllObserver)
            viewModel.getBookmarkTvShows(sortChoice)

            verify {
                val bookmarkTvShows = viewModel.bookmarkTvShows.value
                getAllObserver.onChanged(bookmarkTvShows)
                assertNotNull(bookmarkTvShows)
                Assert.assertEquals(emptyBookmarkTvShows.size, bookmarkTvShows?.size)
            }
        }
    }

    @Test
    fun `delete bookmark tv show success`() {
        runBlockingTest {
            coEvery { bookmarkTvShowRepository.delete(TV_SHOW_ID) } returns DELETE_SUCCESS_RESPONSE
            coEvery { coroutineProvider.background() } returns Dispatchers.Unconfined

            viewModel.deleteBookmarkResult.observeForever(successObserver)
            viewModel.deleteTvShowFromBookmark(TV_SHOW_ID)

            verify {
                val deleteBookmarkResult = viewModel.deleteBookmarkResult.value
                successObserver.onChanged(deleteBookmarkResult)
                assertNotNull(deleteBookmarkResult)
            }
        }
    }

    @Test
    fun `delete bookmark tv show error`() {
        runBlockingTest {
            coEvery { bookmarkTvShowRepository.delete(TV_SHOW_ID) } returns DELETE_FAILED_RESPONSE
            coEvery { coroutineProvider.background() } returns Dispatchers.Unconfined

            viewModel.errorResult.observeForever(deleteFailedObserver)
            viewModel.deleteTvShowFromBookmark(TV_SHOW_ID)

            verify {
                val errorMessage = viewModel.errorResult.value
                deleteFailedObserver.onChanged(errorMessage)
                assertNotNull(errorMessage)
                Assert.assertEquals(RoomConsts.REMOVE_BOOKMARK_FAILED, errorMessage)
            }
        }
    }

    @Test
    fun `add bookmark tv show success`() {
        runBlockingTest {
            val bookmarkTvShowResponse = RoomUtils.getBookmarkTvShowResponse()
            val bookmarkTvShowRequest = mapper.map(bookmarkTvShowResponse)

            coEvery { bookmarkTvShowRepository.add(bookmarkTvShowRequest) } returns ADD_SUCCESS_RESPONSE
            coEvery { coroutineProvider.background() } returns Dispatchers.Unconfined

            viewModel.addBookmarkResult.observeForever(successObserver)
            viewModel.addTvShowToBookmark(bookmarkTvShowResponse)

            verify {
                val addBookmarkResult = viewModel.addBookmarkResult.value
                successObserver.onChanged(addBookmarkResult)
                assertNotNull(addBookmarkResult)
            }
        }
    }

    @Test
    fun `add bookmark tv show error`() {
        runBlockingTest {
            val bookmarkTvShowResponse = RoomUtils.getBookmarkTvShowResponse()
            val bookmarkTvShowRequest = mapper.map(bookmarkTvShowResponse)

            coEvery { bookmarkTvShowRepository.add(bookmarkTvShowRequest) } returns ADD_FAILED_RESPONSE
            coEvery { coroutineProvider.background() } returns Dispatchers.Unconfined

            viewModel.errorResult.observeForever(deleteFailedObserver)
            viewModel.addTvShowToBookmark(bookmarkTvShowResponse)

            verify {
                val errorMessage = viewModel.errorResult.value
                deleteFailedObserver.onChanged(errorMessage)
                assertNotNull(errorMessage)
                Assert.assertEquals(RoomConsts.ADD_BOOKMARK_FAILED, errorMessage)
            }
        }
    }
}