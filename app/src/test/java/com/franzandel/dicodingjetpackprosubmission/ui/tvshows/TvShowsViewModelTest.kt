package com.franzandel.dicodingjetpackprosubmission.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.franzandel.dicodingjetpackprosubmission.external.ErrorCodeData
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.franzandel.dicodingjetpackprosubmission.external.coroutine.CoroutineProvider
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShow
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.repository.TvShowsRepository
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.presentation.viewmodel.TvShowsViewModel
import com.franzandel.dicodingjetpackprosubmission.utils.TestingUtils
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
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Franz Andel on 20/02/21.
 * Android Engineer
 */

@ExperimentalCoroutinesApi
class TvShowsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val tvShowsRepository: TvShowsRepository = mockk(relaxed = true)
    private val coroutineProvider: CoroutineProvider = mockk(relaxed = true)
    private val observer: Observer<List<TvShow>> = mockk(relaxed = true)

    private lateinit var viewModel: TvShowsViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        viewModel = TvShowsViewModel(tvShowsRepository, coroutineProvider)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `get tv shows success`() {
        runBlockingTest {
            val dummyTvShows = TestingUtils.getTvShowsFromJson()
            val tvShowsLiveData = MutableLiveData<Resource<List<TvShow>>>()
            tvShowsLiveData.value = Resource.success(dummyTvShows)

            coEvery { tvShowsRepository.getTvShows() } returns tvShowsLiveData
            coEvery { coroutineProvider.main() } returns Dispatchers.Unconfined
            coEvery { coroutineProvider.background() } returns Dispatchers.Unconfined

            viewModel.result.observeForever(observer)
            viewModel.getTvShows()

            verify {
                val tvShows = viewModel.result.value
                observer.onChanged(tvShows)
                assertNotNull(tvShows)
                Assert.assertEquals(dummyTvShows.size, tvShows?.size)
            }
        }
    }

    @Test
    fun `get empty tv shows`() {
        runBlockingTest {
            val emptyTvShows = emptyList<TvShow>()
            val tvShowsLiveData = MutableLiveData<Resource<List<TvShow>>>()
            tvShowsLiveData.value = Resource.success(emptyTvShows)

            coEvery { tvShowsRepository.getTvShows() } returns tvShowsLiveData
            coEvery { coroutineProvider.main() } returns Dispatchers.Unconfined
            coEvery { coroutineProvider.background() } returns Dispatchers.Unconfined

            viewModel.result.observeForever(observer)
            viewModel.getTvShows()

            verify {
                val tvShows = viewModel.result.value
                observer.onChanged(tvShows)
                assertNotNull(tvShows)
                Assert.assertEquals(emptyTvShows.size, tvShows?.size)
            }
        }
    }

    @Test
    fun `get tv shows error`() {
        runBlockingTest {
            val errorMessage = "Terjadi kesalahan di internet"
            val errorCodeData = ErrorCodeData(400, errorMessage)
            val tvShowsLiveData = MutableLiveData<Resource<List<TvShow>>>()
            tvShowsLiveData.value = Resource.error(emptyList(), errorCodeData)

            coEvery { tvShowsRepository.getTvShows() } returns tvShowsLiveData
            coEvery { coroutineProvider.main() } returns Dispatchers.Unconfined
            coEvery { coroutineProvider.background() } returns Dispatchers.Unconfined

            viewModel.result.observeForever(observer)
            viewModel.getTvShows()

            assertEquals(errorMessage, viewModel.errorResult.value)
        }
    }
}