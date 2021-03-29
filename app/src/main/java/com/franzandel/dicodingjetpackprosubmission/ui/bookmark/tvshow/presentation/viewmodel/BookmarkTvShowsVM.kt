package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.base.BaseViewModel
import com.franzandel.dicodingjetpackprosubmission.external.coroutine.CoroutineProvider
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.repository.BookmarkTvShowRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Franz Andel on 29/03/21.
 * Android Engineer
 */

class BookmarkTvShowsVM @Inject constructor(
    private val bookmarkTvShowsRepository: BookmarkTvShowRepository,
    private val coroutineProvider: CoroutineProvider,
    private val mapper: BaseMapper<BookmarkTvShowResponse, BookmarkTvShowRequest>
) : BaseViewModel() {

    private var _bookmarkTvShowsSource: LiveData<List<BookmarkTvShowResponse>> = MutableLiveData()
    private val _bookmarkTvShows = MediatorLiveData<List<BookmarkTvShowResponse>>()
    val bookmarkTvShows: LiveData<List<BookmarkTvShowResponse>> = _bookmarkTvShows

    private val _deleteBookmarkResult = MutableLiveData<Unit>()
    val deleteBookmarkResult: LiveData<Unit> = _deleteBookmarkResult

    private val _addBookmarkResult = MutableLiveData<Unit>()
    val addBookmarkResult: LiveData<Unit> = _addBookmarkResult

    fun getBookmarkMovies() {
        viewModelScope.launch(coroutineProvider.main()) {
            _loadingResult.value = true
            withContext(coroutineProvider.background()) {
                _bookmarkTvShowsSource = bookmarkTvShowsRepository.getAll()
            }

            _bookmarkTvShows.addSource(_bookmarkTvShowsSource) {
                _bookmarkTvShows.postValue(it)
                _loadingResult.postValue(false)
            }
        }
    }

    fun deleteMovieFromBookmark(id: Int) {
        _loadingResult.value = true
        viewModelScope.launch(coroutineProvider.background()) {
            val deleteResponse = bookmarkTvShowsRepository.delete(id)
            if (deleteResponse >= 0)
                _deleteBookmarkResult.postValue(Unit)
            else
                _errorResult.postValue("")

            _loadingResult.postValue(false)
        }
    }

    fun addMovieToBookmark(bookmarkTvShowResponse: BookmarkTvShowResponse) {
        _loadingResult.value = true
        val bookmarkMovieRequest = mapper.map(bookmarkTvShowResponse)

        viewModelScope.launch(coroutineProvider.background()) {
            val addResponse = bookmarkTvShowsRepository.add(bookmarkMovieRequest)
            if (addResponse >= 0)
                _addBookmarkResult.postValue(Unit)
            else
                _errorResult.postValue("")

            _loadingResult.postValue(false)
        }
    }
}