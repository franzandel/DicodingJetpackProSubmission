package com.franzandel.dicodingjetpackprosubmission.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.franzandel.dicodingjetpackprosubmission.external.ErrorCodeData
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.franzandel.dicodingjetpackprosubmission.external.coroutine.CoroutineProvider
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.repository.MoviesRepository
import com.franzandel.dicodingjetpackprosubmission.ui.movies.presentation.viewmodel.MoviesViewModel
import com.franzandel.dicodingjetpackprosubmission.utils.TestingUtils
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*


/**
 * Created by Franz Andel on 20/02/21.
 * Android Engineer
 */

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val moviesRepository: MoviesRepository = mockk(relaxed = true)
    private val coroutineProvider: CoroutineProvider = mockk(relaxed = true)
    private val observer: Observer<List<Movie>> = mockk(relaxed = true)

    private lateinit var viewModel: MoviesViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(moviesRepository, coroutineProvider)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `get movies success`() {
        runBlockingTest {
            val dummyMovies = TestingUtils.getMoviesFromJson()
            val moviesLiveData = MutableLiveData<Resource<List<Movie>>>()
            moviesLiveData.value = Resource.success(dummyMovies)

            coEvery { moviesRepository.getMovies() } returns moviesLiveData
            coEvery { coroutineProvider.main() } returns Dispatchers.Unconfined
            coEvery { coroutineProvider.background() } returns Dispatchers.Unconfined

            viewModel.result.observeForever(observer)
            viewModel.getMovies()

            verify {
                val movies = viewModel.result.value
                observer.onChanged(movies)
                Assert.assertNotNull(movies)
                assertEquals(dummyMovies.size, movies?.size)
            }
        }
    }

    @Test
    fun `get empty movies`() {
        runBlockingTest {
            val emptyMovies = emptyList<Movie>()
            val moviesLiveData = MutableLiveData<Resource<List<Movie>>>()
            moviesLiveData.value = Resource.success(emptyMovies)

            coEvery { moviesRepository.getMovies() } returns moviesLiveData
            coEvery { coroutineProvider.main() } returns Dispatchers.Unconfined
            coEvery { coroutineProvider.background() } returns Dispatchers.Unconfined

            viewModel.result.observeForever(observer)
            viewModel.getMovies()

            verify {
                val movies = viewModel.result.value
                observer.onChanged(movies)
                Assert.assertNotNull(movies)
                assertEquals(emptyMovies.size, movies?.size)
            }
        }
    }

    @Test
    fun `get movies error`() {
        runBlockingTest {
            val errorMessage = "Terjadi kesalahan di internet"
            val errorCodeData = ErrorCodeData(400, errorMessage)
            val moviesLiveData = MutableLiveData<Resource<List<Movie>>>()
            moviesLiveData.value = Resource.error(emptyList(), errorCodeData)

            coEvery { moviesRepository.getMovies() } returns moviesLiveData
            coEvery { coroutineProvider.main() } returns Dispatchers.Unconfined
            coEvery { coroutineProvider.background() } returns Dispatchers.Unconfined

            viewModel.result.observeForever(observer)
            viewModel.getMovies()

            Assert.assertEquals(errorMessage, viewModel.errorResult.value)
        }
    }
}