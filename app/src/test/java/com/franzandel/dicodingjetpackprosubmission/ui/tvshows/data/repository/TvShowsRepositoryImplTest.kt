package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShow
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShowsResponseDTO
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.mapper.TvShowsResponseDTOMapper
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.remote.TvShowsNetwork
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
 * Created by Franz Andel on 21/03/21.
 * Android Engineer
 */

@ExperimentalCoroutinesApi
class TvShowsRepositoryImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockWebServer = MockWebServer()
    private val tvShowsNetwork = Retrofit.Builder()
        .baseUrl(mockWebServer.url(""))
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(TvShowsNetwork::class.java)
    private val gson: Gson = Gson()

    private val observer: Observer<Resource<List<TvShow>>> = mockk(relaxed = true)
    private val mapper: BaseMapper<TvShowsResponseDTO, List<TvShow>> = TvShowsResponseDTOMapper()

    private lateinit var repository: TvShowsRepository

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        repository = TvShowsRepositoryImpl(tvShowsNetwork, mapper, gson)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `get tv shows api success`() {
        runBlocking {
            mockWebServer.enqueueResponse("tv_shows_response.json")
            val dummyTvShows = TestingUtils.getTvShowsFromJson()

            val tvShowsResource = repository.getTvShows()
            tvShowsResource.observeForever(observer)
            val tvShows = tvShowsResource.value?.data

            verify {
                observer.onChanged(tvShowsResource.value)
                assertNotNull(tvShows)
                Assert.assertEquals(dummyTvShows.size, tvShows?.size)
            }
        }
    }

    @Test
    fun `get tv shows api error`() {
        runBlocking {
            val errorMessage = "The resource you requested could not be found."
            mockWebServer.enqueueResponse(
                fileName = "error_response.json",
                responseCode = 404
            )

            val tvShowsResource = repository.getTvShows()
            tvShowsResource.observeForever(observer)

            verify {
                observer.onChanged(tvShowsResource.value)
                Assert.assertEquals(errorMessage, tvShowsResource.value?.errorCodeData?.message)
            }
        }
    }
}