package com.franzandel.dicodingjetpackprosubmission.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TvShowsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Tv Shows Fragment"
    }
    val text: LiveData<String> = _text
}