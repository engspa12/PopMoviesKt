package com.example.dbm.popularmovieskt.global

import com.example.dbm.popularmovieskt.BuildConfig

object Constants {
    val API_KEY = BuildConfig.API_KEY
    val LANGUAGE = "en-US"
    val API_KEY_PARAM = "api_key"
    val LANGUAGE_PARAM = "language"
    val SORT_BY_POPULAR = "popular"
    val PAGE_PARAM = "page"
    val BASE_URL = "https://api.themoviedb.org/3/movie/"
    val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500"
    val YOUTUBE_URL = "https://www.youtube.com/watch?v="
    val NUM_GRID_ITEMS = 20
    val NUM_TOTAL = 100
    val PAGE_INITIAL_VALUE = 1
}