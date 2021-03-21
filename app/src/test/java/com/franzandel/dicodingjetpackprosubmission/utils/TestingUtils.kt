package com.franzandel.dicodingjetpackprosubmission.utils

import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.external.extension.convertStringToMap
import com.franzandel.dicodingjetpackprosubmission.external.extension.toDataClass
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.MoviesResponseDTO
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.mapper.MoviesResponseDTOMapper
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShow
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShowsResponseDTO
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.mapper.TvShowsResponseDTOMapper
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter

/**
 * Created by Franz Andel on 15/03/21.
 * Android Engineer
 */

object TestingUtils {

    fun getMoviesFromJson(): List<Movie> {
        val inputStream =
            javaClass.classLoader?.getResourceAsStream("movies_response.json")

        val writer = StringWriter()
        val buffer = CharArray(1024)
        inputStream.use { stream ->
            val reader = BufferedReader(InputStreamReader(stream, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }

        val jsonString = writer.toString()

        val dataInMap: Map<String, Any> = jsonString.convertStringToMap()
        val moviesResponseDTO = dataInMap.toDataClass<MoviesResponseDTO>()
        val mapper: BaseMapper<MoviesResponseDTO, List<Movie>> = MoviesResponseDTOMapper()

        return mapper.map(moviesResponseDTO)
    }

    fun getTvShowsFromJson(): List<TvShow> {
        val inputStream =
            javaClass.classLoader?.getResourceAsStream("tv_shows_response.json")

        val writer = StringWriter()
        val buffer = CharArray(1024)
        inputStream.use { stream ->
            val reader = BufferedReader(InputStreamReader(stream, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }

        val jsonString = writer.toString()

        val dataInMap: Map<String, Any> = jsonString.convertStringToMap()
        val tvShowsResponseDTO = dataInMap.toDataClass<TvShowsResponseDTO>()
        val mapper: BaseMapper<TvShowsResponseDTO, List<TvShow>> = TvShowsResponseDTOMapper()

        return mapper.map(tvShowsResponseDTO)
    }

    fun MockWebServer.enqueueResponse(
        fileName: String,
        headers: Map<String, String> = emptyMap(),
        responseCode: Int = 200
    ) {
        val inputStream =
            javaClass.classLoader?.getResourceAsStream(fileName)
        val source = inputStream?.source()?.buffer()
        val mockResponse = MockResponse()

        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }

        this.enqueue(
            mockResponse.setResponseCode(responseCode).setBody(source?.readString(Charsets.UTF_8))
        )
    }
}