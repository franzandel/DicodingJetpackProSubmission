package com.franzandel.dicodingjetpackprosubmission.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.dao.BookmarkMovieDao
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieDTO
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.dao.BookmarkTvShowDao
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowDTO

/**
 * Created by Franz Andel on 25/03/21.
 * Android Engineer
 */

@Database(
    entities = [BookmarkMovieDTO::class, BookmarkTvShowDTO::class],
    version = 1,
    exportSchema = false
)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun bookmarkMovieDao(): BookmarkMovieDao
    abstract fun bookmarkTvShowDao(): BookmarkTvShowDao
}