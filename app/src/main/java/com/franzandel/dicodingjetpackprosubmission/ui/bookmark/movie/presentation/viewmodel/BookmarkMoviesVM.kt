package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.franzandel.dicodingjetpackprosubmission.base.BaseViewModel
import com.franzandel.dicodingjetpackprosubmission.external.coroutine.CoroutineProvider
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.repository.BookmarkMovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Franz Andel on 28/03/21.
 * Android Engineer
 */

class BookmarkMoviesVM @Inject constructor(
    private val bookmarkMovieRepository: BookmarkMovieRepository,
    private val coroutineProvider: CoroutineProvider
) : BaseViewModel() {

    private val _bookmarkMovies = MutableLiveData<List<BookmarkMovieResponse>>()
    val bookmarkMovies: LiveData<List<BookmarkMovieResponse>> = _bookmarkMovies

    fun getBookmarkMovies() {
        viewModelScope.launch(coroutineProvider.background()) {
            _bookmarkMovies.postValue(bookmarkMovieRepository.getAll())
        }
    }
}