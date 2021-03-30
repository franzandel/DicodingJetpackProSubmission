package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieDTO

/**
 * Created by Franz Andel on 25/03/21.
 * Android Engineer
 */

@Dao
interface BookmarkMovieDao {

    @Query("SELECT * FROM tbl_bookmark_movie")
    fun getBookmarkMovies(): DataSource.Factory<Int, BookmarkMovieDTO>

    @Query("SELECT * FROM tbl_bookmark_movie WHERE id = :id")
    suspend fun getBookmarkMovie(id: Int): BookmarkMovieDTO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarkMovie(bookmarkMovieDTO: BookmarkMovieDTO): Long

    @Query("DELETE FROM tbl_bookmark_movie WHERE id = :id")
    suspend fun deleteBookmarkMovie(id: Int): Int
}