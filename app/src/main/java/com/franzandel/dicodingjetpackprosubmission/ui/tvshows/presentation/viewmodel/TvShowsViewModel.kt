package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.presentation.viewmodel

import androidx.lifecycle.*
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShow
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.repository.TvShowsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowsViewModel(private val tvShowsRepository: TvShowsRepository) : ViewModel() {

    private var _tvShowsSource: LiveData<Resource<List<TvShow>>> = MutableLiveData()
    private val _tvShowsResult = MediatorLiveData<List<TvShow>>()
    val tvShowsResult: LiveData<List<TvShow>> = _tvShowsResult

    private val _tvShowsErrorResult = MediatorLiveData<String>()
    val tvShowsErrorResult: LiveData<String> = _tvShowsErrorResult

    private val _tvShowsLoadingResult = MediatorLiveData<Boolean>()
    val tvShowsLoadingResult: LiveData<Boolean> = _tvShowsLoadingResult

    fun getTvShows() {
        viewModelScope.launch(Dispatchers.Main) {
            _tvShowsLoadingResult.value = true
            withContext(Dispatchers.IO) {
                _tvShowsSource = tvShowsRepository.getTvShows()
            }

            _tvShowsResult.addSource(_tvShowsSource) {
                when (it.status) {
                    Resource.Status.SUCCESS -> _tvShowsResult.postValue(it.data!!)
                    Resource.Status.ERROR -> _tvShowsErrorResult.postValue(it.errorCodeData?.message!!)
                    Resource.Status.LOADING -> _tvShowsLoadingResult.postValue(false)
                }
            }
        }
    }
}