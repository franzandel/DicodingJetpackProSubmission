package com.franzandel.dicodingjetpackprosubmission.ui.movies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.franzandel.dicodingjetpackprosubmission.base.BaseViewModel
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    BaseViewModel() {

    private var _source: LiveData<Resource<List<Movie>>> = MutableLiveData()
    private val _result = MediatorLiveData<List<Movie>>()
    val result: LiveData<List<Movie>> = _result

    fun getMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            _loadingResult.value = true
            withContext(Dispatchers.IO) {
                _source = moviesRepository.getMovies()
            }

            _result.addSource(_source) {
                when (it.status) {
                    Resource.Status.SUCCESS -> _result.postValue(it.data!!)
                    Resource.Status.ERROR -> _errorResult.postValue(it.errorCodeData?.message!!)
                }
                _loadingResult.postValue(false)
            }
        }
    }
}