package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Franz Andel on 27/03/21.
 * Android Engineer
 */

@Entity(tableName = "tbl_bookmark_tv_show")
data class BookmarkTvShowDTO(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,
    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String,
    val name: String,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String,
    @ColumnInfo(name = "original_name")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int
)