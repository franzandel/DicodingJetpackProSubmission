package com.franzandel.dicodingjetpackprosubmission.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.franzandel.dicodingjetpackprosubmission.data.EpicMovieData
import com.franzandel.dicodingjetpackprosubmission.data.entity.TvShow

class TvShowsViewModel : ViewModel() {

    private val _tvShows = MutableLiveData<List<TvShow>>()
    val tvShows: LiveData<List<TvShow>> = _tvShows

    fun getTvShows() {
        _tvShows.value = EpicMovieData.getAllTvShows()
    }
}