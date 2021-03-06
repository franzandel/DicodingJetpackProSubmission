package com.franzandel.dicodingjetpackprosubmission.ui.movies.presentation.viewmodel

import androidx.lifecycle.*
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private var _moviesSource: LiveData<Resource<List<Movie>>> = MutableLiveData()
    private val _moviesResult = MediatorLiveData<List<Movie>>()
    val moviesResult: LiveData<List<Movie>> = _moviesResult

    private val _moviesErrorResult = MediatorLiveData<String>()
    val moviesErrorResult: LiveData<String> = _moviesErrorResult

    private val _moviesLoadingResult = MediatorLiveData<Boolean>()
    val moviesLoadingResult: LiveData<Boolean> = _moviesLoadingResult

    fun getMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            _moviesLoadingResult.value = true
            withContext(Dispatchers.IO) {
                _moviesSource = moviesRepository.getMovies()
            }

            _moviesResult.addSource(_moviesSource) {
                when (it.status) {
                    Resource.Status.SUCCESS -> _moviesResult.postValue(it.data!!)
                    Resource.Status.ERROR -> _moviesErrorResult.postValue(it.errorCodeData?.message!!)
                    Resource.Status.LOADING -> _moviesLoadingResult.postValue(false)
                }
            }
        }
    }
}