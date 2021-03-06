package com.franzandel.dicodingjetpackprosubmission.ui.movies.presentation

import androidx.lifecycle.*
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.entity.MoviesResponseDTO
import com.franzandel.dicodingjetpackprosubmission.ui.movies.data.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private var _moviesSource: LiveData<Resource<MoviesResponseDTO>> = MutableLiveData()
    private val _moviesResult = MediatorLiveData<MoviesResponseDTO>()
    val moviesResult: LiveData<MoviesResponseDTO> = _moviesResult

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