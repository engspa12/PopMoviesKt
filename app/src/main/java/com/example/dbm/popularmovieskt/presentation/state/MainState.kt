package com.example.dbm.popularmovieskt.presentation.state

import com.example.dbm.popularmovieskt.presentation.model.MovieView

sealed class MainState(
    val value: List<MovieView>? = null,
    val errorMessage: String = "",
    val loadingMessage: String = ""
) {
    class Success(value: List<MovieView>): MainState(value)
    class Error(errorMessage: String): MainState(errorMessage = errorMessage)
    class Loading(loadingMessage: String) : MainState(loadingMessage = loadingMessage)
}