package com.franzandel.dicodingjetpackprosubmission.ui.movies.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.MoviesResponseDTO
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.mapper.MoviesResponseDTOMapper
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.remote.MoviesNetwork
import com.franzandel.dicodingjetpackprosubmission.utils.TestingUtils
import com.franzandel.dicodingjetpackprosubmission.utils.TestingUtils.enqueueResponse
import com.google.gson.Gson
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Franz Andel on 18/03/21.
 * Android Engineer
 */

@ExperimentalCoroutinesApi
class MoviesRepositoryImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockWebServer = MockWebServer()
    private val moviesNetwork = Retrofit.Builder()
        .baseUrl(mockWebServer.url(""))
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(MoviesNetwork::class.java)
    private val gson: Gson = Gson()

    private val observer: Observer<Resource<List<Movie>>> = mockk(relaxed = true)
    private val mapper: BaseMapper<MoviesResponseDTO, List<Movie>> = MoviesResponseDTOMapper()

    private lateinit var repository: MoviesRepository

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        repository = MoviesRepositoryImpl(moviesNetwork, mapper, gson)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `get movies api success`() {
        runBlocking {
            mockWebServer.enqueueResponse("movies_response.json")
            val dummyMovies = TestingUtils.getMoviesFromJson()

            val moviesResource = repository.getMovies()
            moviesResource.observeForever(observer)
            val movies = moviesResource.value?.data

            verify {
                observer.onChanged(moviesResource.value)
                assertNotNull(movies)
                Assert.assertEquals(dummyMovies.size, movies?.size)
            }
        }
    }

    @Test
    fun `get movies api error`() {
        runBlocking {
            val errorMessage = "The resource you requested could not be found."
            mockWebServer.enqueueResponse(
                fileName = "movies_error_response.json",
                responseCode = 404
            )

            val moviesResource = repository.getMovies()
            moviesResource.observeForever(observer)

            verify {
                observer.onChanged(moviesResource.value)
                Assert.assertEquals(errorMessage, moviesResource.value?.errorCodeData?.message)
            }
        }
    }
}