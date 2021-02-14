package com.franzandel.dicodingjetpackprosubmission.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.franzandel.dicodingjetpackprosubmission.data.EpicMovieData
import com.franzandel.dicodingjetpackprosubmission.data.entity.Movie

class MoviesViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    fun getMovies() {
        _movies.value = EpicMovieData.getAllMovies()
    }
}