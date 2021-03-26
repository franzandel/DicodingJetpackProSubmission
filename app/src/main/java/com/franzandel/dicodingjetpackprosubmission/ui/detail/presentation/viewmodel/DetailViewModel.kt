package com.franzandel.dicodingjetpackprosubmission.ui.detail.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.base.BaseViewModel
import com.franzandel.dicodingjetpackprosubmission.external.coroutine.CoroutineProvider
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.repository.BookmarkMovieRepository
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Franz Andel on 25/03/21.
 * Android Engineer
 */

class DetailViewModel @Inject constructor(
    private val bookmarkMovieRepository: BookmarkMovieRepository,
    private val coroutineProvider: CoroutineProvider,
    private val mapper: BaseMapper<Movie, BookmarkMovieRequest>
) : BaseViewModel() {

    private val _bookmarkMovieResult = MutableLiveData<Unit>()
    val bookmarkMovieResult: LiveData<Unit> = _bookmarkMovieResult

    fun addMovieToBookmark(movie: Movie) {
        _loadingResult.value = true
        val bookmarkMovieRequest = mapper.map(movie)

        viewModelScope.launch(coroutineProvider.background()) {
            val addResponse = bookmarkMovieRepository.add(bookmarkMovieRequest)
            if (addResponse >= 0)
                _bookmarkMovieResult.postValue(Unit)
            else
                _errorResult.postValue("")

            _loadingResult.postValue(false)
        }
    }

    fun deleteMovieFromBookmark(id: Int) {
        _loadingResult.value = true
        viewModelScope.launch(coroutineProvider.background()) {
            val deleteResponse = bookmarkMovieRepository.delete(id)
            if (deleteResponse >= 0)
                _bookmarkMovieResult.postValue(Unit)
            else
                _errorResult.postValue("")

            _loadingResult.postValue(false)
        }
    }

    fun addTvShowToBookmark() {

    }
}