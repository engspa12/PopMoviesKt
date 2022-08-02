package com.example.dbm.popularmovieskt.presentation.state

import com.example.dbm.popularmovieskt.presentation.model.MovieGridView
import com.example.dbm.popularmovieskt.util.StringWrapper

sealed class MainState(
    val value: List<MovieGridView>? = null,
    val errorMessage: StringWrapper = StringWrapper.SimpleString(""),
    val loadingMessage: StringWrapper = StringWrapper.SimpleString("")
) {
    class Success(value: List<MovieGridView>): MainState(value)
    class Error(errorMessage: StringWrapper): MainState(errorMessage = errorMessage)
    class Loading(loadingMessage: StringWrapper) : MainState(loadingMessage = loadingMessage)
}