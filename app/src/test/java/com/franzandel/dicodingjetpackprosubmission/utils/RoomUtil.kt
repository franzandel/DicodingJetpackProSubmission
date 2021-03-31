package com.franzandel.dicodingjetpackprosubmission.utils

import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowResponse

/**
 * Created by Franz Andel on 31/03/21.
 * Android Engineer
 */

object RoomUtil {

    fun getBookmarkMovies(): List<BookmarkMovieResponse> {
        val bookmarkMovies = mutableListOf<BookmarkMovieResponse>()
        bookmarkMovies.add(
            BookmarkMovieResponse(
                id = 399566,
                adult = false,
                backdropPath = "/iopYFB1b6Bh7FWZh3onQhph1sih.jpg",
                originalLanguage = "en",
                originalTitle = "Godzilla vs. Kong",
                overview = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                popularity = 9043.741,
                posterPath = "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                releaseDate = "2021-03-24",
                title = "Godzilla vs. Kong",
                video = false,
                voteAverage = 7.1,
                voteCount = 155
            )
        )
        bookmarkMovies.add(
            BookmarkMovieResponse(
                id = 458576,
                adult = false,
                backdropPath = "/z8TvnEVRenMSTemxYZwLGqFofgF.jpg",
                originalLanguage = "en",
                originalTitle = "Monster Hunter",
                overview = "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                popularity = 2021.063,
                posterPath = "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                releaseDate = "2020-12-03",
                title = "Monster Hunter",
                video = false,
                voteAverage = 7.1,
                voteCount = 1292
            )
        )
        bookmarkMovies.add(
            BookmarkMovieResponse(
                id = 527774,
                adult = false,
                backdropPath = "/hJuDvwzS0SPlsE6MNFOpznQltDZ.jpg",
                originalLanguage = "en",
                originalTitle = "Raya and the Last Dragon",
                overview = "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                popularity = 3152.527,
                posterPath = "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                releaseDate = "2021-03-03",
                title = "Raya and the Last Dragon",
                video = false,
                voteAverage = 8.3,
                voteCount = 1815
            )
        )
        bookmarkMovies.add(
            BookmarkMovieResponse(
                id = 581389,
                adult = false,
                backdropPath = "/drulhSX7P5TQlEMQZ3JoXKSDEfz.jpg",
                originalLanguage = "ko",
                originalTitle = "승리호",
                overview = "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
                popularity = 3228.143,
                posterPath = "/1e1tUWInXCVrrwY6ntuNRuwEj7P.jpg",
                releaseDate = "2021-02-05",
                title = "Space Sweepers",
                video = false,
                voteAverage = 7.2,
                voteCount = 443
            )
        )
        bookmarkMovies.add(
            BookmarkMovieResponse(
                id = 791373,
                adult = false,
                backdropPath = "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                originalLanguage = "en",
                originalTitle = "Zack Snyder's Justice League",
                overview = "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                popularity = 11424.918,
                posterPath = "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                releaseDate = "2021-03-18",
                title = "Zack Snyder's Justice League",
                video = false,
                voteAverage = 8.7,
                voteCount = 3789
            )
        )
        return bookmarkMovies
    }

    fun getBookmarkTvShows(): List<BookmarkTvShowResponse> {
        val bookmarkTvShows = mutableListOf<BookmarkTvShowResponse>()
        bookmarkTvShows.add(
            BookmarkTvShowResponse(
                id = 88396,
                backdropPath = "/JB17sIsU53NuWVUecOwrCA0CUp.jpg",
                firstAirDate = "2021-03-19",
                name = "The Falcon and the Winter Soldier",
                originalLanguage = "en",
                originalName = "The Falcon and the Winter Soldier",
                overview = "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                popularity = 5436.012,
                posterPath = "/qGA31gx01iIJMgk2MyVgurhCGeO.jpg",
                voteAverage = 7.8,
                voteCount = 2122
            )
        )
        bookmarkTvShows.add(
            BookmarkTvShowResponse(
                id = 85271,
                backdropPath = "/rFLF2QTZL37Yjdc6kmV0PbrYz3w.jpg",
                firstAirDate = "2021-01-15",
                name = "WandaVision",
                originalLanguage = "en",
                originalName = "WandaVision",
                overview = "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                popularity = 894.651,
                posterPath = "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                voteAverage = 8.5,
                voteCount = 7793
            )
        )
        return bookmarkTvShows
    }
}