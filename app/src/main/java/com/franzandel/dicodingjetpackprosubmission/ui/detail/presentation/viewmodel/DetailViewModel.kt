package com.franzandel.dicodingjetpackprosubmission.ui.detail.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.base.BaseViewModel
import com.franzandel.dicodingjetpackprosubmission.external.coroutine.CoroutineProvider
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.entity.FavoriteMovieRequest
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.repository.FavoriteMovieRepository
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Franz Andel on 25/03/21.
 * Android Engineer
 */

class DetailViewModel @Inject constructor(
    private val favoriteMovieRepository: FavoriteMovieRepository,
    private val coroutineProvider: CoroutineProvider,
    private val mapper: BaseMapper<Movie, FavoriteMovieRequest>
) : BaseViewModel() {

    private val _favoriteMovieResult = MutableLiveData<Unit>()
    val favoriteMovieResult: LiveData<Unit> = _favoriteMovieResult

    fun addMovieToFavorite(movie: Movie) {
        _loadingResult.value = true
        val favoriteMovieRequest = mapper.map(movie)

        viewModelScope.launch(coroutineProvider.background()) {
            val addResponse = favoriteMovieRepository.add(favoriteMovieRequest)
            if (addResponse >= 0)
                _favoriteMovieResult.postValue(Unit)
            else
                _errorResult.postValue("")

            _loadingResult.postValue(false)
        }
    }

    fun deleteMovieFromFavorite(id: Int) {
        _loadingResult.value = true
        viewModelScope.launch(coroutineProvider.background()) {
            val deleteResponse = favoriteMovieRepository.delete(id)
            if (deleteResponse >= 0)
                _favoriteMovieResult.postValue(Unit)
            else
                _errorResult.postValue("")

            _loadingResult.postValue(false)
        }
    }

    fun addTvShowToFavorite() {

    }
}