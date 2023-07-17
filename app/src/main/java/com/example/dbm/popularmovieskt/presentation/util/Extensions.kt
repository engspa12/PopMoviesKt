package com.example.dbm.popularmovieskt.presentation.util

import com.example.dbm.popularmovieskt.R

fun MoviesViewError.mapToStringResource(): Int {
    return when(this){
        MoviesViewError.NO_FAVORITE_MOVIES_FOUND -> R.string.empty_movies_list
        MoviesViewError.NO_INTERNET_CONNECTION -> R.string.no_internet_connection
        MoviesViewError.GENERIC -> R.string.error_retrieving_data
        MoviesViewError.UNKNOWN -> R.string.error_unknown
        MoviesViewError.NONE -> R.string.error_unknown
    }
}