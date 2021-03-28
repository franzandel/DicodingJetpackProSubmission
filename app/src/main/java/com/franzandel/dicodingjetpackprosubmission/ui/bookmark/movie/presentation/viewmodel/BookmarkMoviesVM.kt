package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.base.BaseViewModel
import com.franzandel.dicodingjetpackprosubmission.external.coroutine.CoroutineProvider
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.repository.BookmarkMovieRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Franz Andel on 28/03/21.
 * Android Engineer
 */

class BookmarkMoviesVM @Inject constructor(
    private val bookmarkMovieRepository: BookmarkMovieRepository,
    private val coroutineProvider: CoroutineProvider,
    private val mapper: BaseMapper<BookmarkMovieResponse, BookmarkMovieRequest>
) : BaseViewModel() {

    private var _bookmarkMoviesSource: LiveData<List<BookmarkMovieResponse>> = MutableLiveData()
    private val _bookmarkMovies = MediatorLiveData<List<BookmarkMovieResponse>>()
    val bookmarkMovies: LiveData<List<BookmarkMovieResponse>> = _bookmarkMovies

    private val _deleteBookmarkResult = MutableLiveData<Unit>()
    val deleteBookmarkResult: LiveData<Unit> = _deleteBookmarkResult

    private val _addBookmarkResult = MutableLiveData<Unit>()
    val addBookmarkResult: LiveData<Unit> = _addBookmarkResult

    fun getBookmarkMovies() {
        viewModelScope.launch(coroutineProvider.main()) {
            _loadingResult.value = true
            withContext(coroutineProvider.background()) {
                _bookmarkMoviesSource = bookmarkMovieRepository.getAll()
            }

            _bookmarkMovies.addSource(_bookmarkMoviesSource) {
                _bookmarkMovies.postValue(it)
                _loadingResult.postValue(false)
            }
        }
    }

    fun deleteMovieFromBookmark(id: Int) {
        _loadingResult.value = true
        viewModelScope.launch(coroutineProvider.background()) {
            val deleteResponse = bookmarkMovieRepository.delete(id)
            if (deleteResponse >= 0)
                _deleteBookmarkResult.postValue(Unit)
            else
                _errorResult.postValue("")

            _loadingResult.postValue(false)
        }
    }

    fun addMovieToBookmark(bookmarkMovieResponse: BookmarkMovieResponse) {
        _loadingResult.value = true
        val bookmarkMovieRequest = mapper.map(bookmarkMovieResponse)

        viewModelScope.launch(coroutineProvider.background()) {
            val addResponse = bookmarkMovieRepository.add(bookmarkMovieRequest)
            if (addResponse >= 0)
                _addBookmarkResult.postValue(Unit)
            else
                _errorResult.postValue("")

            _loadingResult.postValue(false)
        }
    }
}