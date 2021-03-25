package com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.repository

import androidx.lifecycle.LiveData
import com.franzandel.dicodingjetpackprosubmission.base.BaseMapper
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.dao.FavoriteMovieDao
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.entity.FavoriteMovieDTO
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.entity.FavoriteMovieRequest
import com.franzandel.dicodingjetpackprosubmission.ui.favorite.movie.data.entity.FavoriteMovieResponse
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class FavoriteMovieRepositoryImpl @Inject constructor(
    private val dao: FavoriteMovieDao,
    private val requestMapper: BaseMapper<FavoriteMovieRequest, FavoriteMovieDTO>,
    private val responsesMapper: BaseMapper<LiveData<List<FavoriteMovieDTO>>, LiveData<List<FavoriteMovieResponse>>>
) : FavoriteMovieRepository {

    override suspend fun add(favoriteMovieRequest: FavoriteMovieRequest): Long {
        val favoriteMovieRequestDTO = requestMapper.map(favoriteMovieRequest)
        return dao.insertFavoriteMovie(favoriteMovieRequestDTO)
    }

    override suspend fun delete(id: Int): Int = dao.deleteFavoriteMovie(id)

    override suspend fun getAll(): LiveData<List<FavoriteMovieResponse>> =
        responsesMapper.map(dao.getFavoriteMovies())
}