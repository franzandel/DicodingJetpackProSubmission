package com.franzandel.dicodingjetpackprosubmission.ui.tvshows.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.franzandel.dicodingjetpackprosubmission.base.BaseViewModel
import com.franzandel.dicodingjetpackprosubmission.external.Resource
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.entity.TvShow
import com.franzandel.dicodingjetpackprosubmission.ui.tvshows.data.repository.TvShowsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvShowsViewModel @Inject constructor(private val tvShowsRepository: TvShowsRepository) :
    BaseViewModel() {

    private var _source: LiveData<Resource<List<TvShow>>> = MutableLiveData()
    private val _result = MediatorLiveData<List<TvShow>>()
    val result: LiveData<List<TvShow>> = _result

    fun getTvShows() {
        viewModelScope.launch(Dispatchers.Main) {
            _loadingResult.value = true
            withContext(Dispatchers.IO) {
                _source = tvShowsRepository.getTvShows()
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