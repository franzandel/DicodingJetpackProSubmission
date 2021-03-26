package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.mapper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieDTO
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * Created by Franz Andel on 25/03/21.
 * Android Engineer
 */

@ActivityScoped
class BookmarkMoviesResponseDTOMapper @Inject constructor() :
    BaseMapper<LiveData<List<BookmarkMovieDTO>>, LiveData<List<BookmarkMovieResponse>>>() {

    override fun map(dataModel: LiveData<List<BookmarkMovieDTO>>): LiveData<List<BookmarkMovieResponse>> {
        val mutableBookmarkMovies = MutableLiveData<List<BookmarkMovieResponse>>()
        val bookmarkMoviesResponse = dataModel.value?.map {
            with(it) {
                BookmarkMovieResponse(
                    id = id,
                    adult = adult,
                    backdropPath = backdropPath,
                    originalLanguage = originalLanguage,
                    originalTitle = originalTitle,
                    overview = overview,
                    popularity = popularity,
                    posterPath = posterPath,
                    releaseDate = releaseDate,
                    title = title,
                    video = video,
                    voteAverage = voteAverage,
                    voteCount = voteCount
                )
            }
        } ?: listOf()
        mutableBookmarkMovies.postValue(bookmarkMoviesResponse)

        return mutableBookmarkMovies
    }
}