package com.franzandel.dicodingjetpackprosubmission.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Franz Andel on 14/03/21.
 * Android Engineer
 */

abstract class BaseViewModel : ViewModel() {

    protected val _errorResult = MediatorLiveData<String>()
    val errorResult: LiveData<String> = _errorResult

    protected val _loadingResult = MediatorLiveData<Boolean>()
    val loadingResult: LiveData<Boolean> = _loadingResult
}