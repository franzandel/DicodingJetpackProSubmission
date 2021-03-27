package com.franzandel.dicodingjetpackprosubmission.ui.detail.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.base.BaseViewModel
import com.franzandel.dicodingjetpackprosubmission.external.coroutine.CoroutineProvider
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.repository.BookmarkMovieRepository
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowRequest
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.repository.BookmarkTvShowRepository
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Franz Andel on 25/03/21.
 * Android Engineer
 */

class DetailViewModel @Inject constructor(
    private val bookmarkMovieRepository: BookmarkMovieRepository,
    private val bookmarkTvShowRepository: BookmarkTvShowRepository,
    private val coroutineProvider: CoroutineProvider,
    private val movieRequestMapper: BaseMapper<Movie, BookmarkMovieRequest>,
    private val tvShowRequestMapper: BaseMapper<TvShow, BookmarkTvShowRequest>
) : BaseViewModel() {

    private val _isBookmarked = MutableLiveData<Boolean>()
    val isBookmarked: LiveData<Boolean> = _isBookmarked

    private val _bookmarkResult = MutableLiveData<Unit>()
    val bookmarkResult: LiveData<Unit> = _bookmarkResult

    fun getBookmarkMovie(id: Int) {
        _loadingResult.value = true
        viewModelScope.launch(coroutineProvider.background()) {
            val bookmarkMovieResponse = bookmarkMovieRepository.get(id)
            val isBookmarked = bookmarkMovieResponse != null
            _isBookmarked.postValue(isBookmarked)
            _loadingResult.postValue(false)
        }
    }

    fun addMovieToBookmark(movie: Movie) {
        _loadingResult.value = true
        val bookmarkMovieRequest = movieRequestMapper.map(movie)

        viewModelScope.launch(coroutineProvider.background()) {
            val addResponse = bookmarkMovieRepository.add(bookmarkMovieRequest)
            if (addResponse >= 0)
                _bookmarkResult.postValue(Unit)
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
                _bookmarkResult.postValue(Unit)
            else
                _errorResult.postValue("")

            _loadingResult.postValue(false)
        }
    }

    fun getBookmarkTvShow(id: Int) {
        _loadingResult.value = true
        viewModelScope.launch(coroutineProvider.background()) {
            val bookmarkTvShowResponse = bookmarkTvShowRepository.get(id)
            val isBookmarked = bookmarkTvShowResponse != null
            _isBookmarked.postValue(isBookmarked)
            _loadingResult.postValue(false)
        }
    }

    fun addTvShowToBookmark(tvShow: TvShow) {
        _loadingResult.value = true
        val bookmarkTvShowRequest = tvShowRequestMapper.map(tvShow)

        viewModelScope.launch(coroutineProvider.background()) {
            val addResponse = bookmarkTvShowRepository.add(bookmarkTvShowRequest)
            if (addResponse >= 0)
                _bookmarkResult.postValue(Unit)
            else
                _errorResult.postValue("")

            _loadingResult.postValue(false)
        }
    }

    fun deleteTvShowFromBookmark(id: Int) {
        _loadingResult.value = true
        viewModelScope.launch(coroutineProvider.background()) {
            val deleteResponse = bookmarkTvShowRepository.delete(id)
            if (deleteResponse >= 0)
                _bookmarkResult.postValue(Unit)
            else
                _errorResult.postValue("")

            _loadingResult.postValue(false)
        }
    }
}