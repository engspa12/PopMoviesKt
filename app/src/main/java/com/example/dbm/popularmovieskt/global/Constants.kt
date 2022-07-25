package com.example.dbm.popularmovieskt.global

import com.example.dbm.popularmovieskt.BuildConfig

object Constants {
    const val API_KEY = BuildConfig.API_KEY
    const val LANGUAGE = "en-US"
    const val SORT_BY_POPULAR = "popular"
    const val SORT_BY_HIGHEST_RATED = "top_rated"
    const val SORT_BY_FAVORITE_MOVIES = "favorite_movies"
    const val POPULAR_TITLE = "Popular Movies"
    const val HIGHEST_RATED_TITLE = "Highest Rated Movies"
    const val FAVORITES_TITLE = "Favorite Movies"
    const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500"
    const val YOUTUBE_URL = "https://www.youtube.com/watch?v="
    const val MAX_PAGES = 5
    const val PAGE_INITIAL_VALUE = 1

    enum class NavType {
        NAV_MAIN,
        NAV_DETAIL
    }
}