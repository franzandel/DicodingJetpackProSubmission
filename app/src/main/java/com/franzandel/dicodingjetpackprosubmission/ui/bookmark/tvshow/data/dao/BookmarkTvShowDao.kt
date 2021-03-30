package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.dao

import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowDTO

/**
 * Created by Franz Andel on 27/03/21.
 * Android Engineer
 */

@Dao
interface BookmarkTvShowDao {

    @RawQuery(observedEntities = [BookmarkTvShowDTO::class])
    fun getBookmarkTvShows(query: SupportSQLiteQuery): DataSource.Factory<Int, BookmarkTvShowDTO>

    @Query("SELECT * FROM tbl_bookmark_tv_show WHERE id = :id")
    suspend fun getBookmarkTvShow(id: Int): BookmarkTvShowDTO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarkTvShow(bookmarkTvShowDTO: BookmarkTvShowDTO): Long

    @Query("DELETE FROM tbl_bookmark_tv_show WHERE id = :id")
    suspend fun deleteBookmarkTvShow(id: Int): Int
}