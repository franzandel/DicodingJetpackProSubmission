package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowDTO

/**
 * Created by Franz Andel on 27/03/21.
 * Android Engineer
 */

@Dao
interface BookmarkTvShowDao {

    @Query("SELECT * FROM tbl_bookmark_tv_show")
    fun getBookmarkTvShows(): LiveData<List<BookmarkTvShowDTO>>

    @Query("SELECT * FROM tbl_bookmark_tv_show WHERE id = :id")
    suspend fun getBookmarkTvShow(id: Int): BookmarkTvShowDTO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarkTvShow(bookmarkTvShowDTO: BookmarkTvShowDTO): Long

    @Query("DELETE FROM tbl_bookmark_tv_show WHERE id = :id")
    suspend fun deleteBookmarkTvShow(id: Int): Int
}