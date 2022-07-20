package com.example.dbm.popularmovieskt.presentation.state

import com.example.dbm.popularmovieskt.presentation.model.MovieDetailsView

sealed class MainState(
    val value: List<MovieDetailsView>? = null,
    val errorMessage: String = "",
    val loadingMessage: String = ""
) {
    class Success(value: List<MovieDetailsView>): MainState(value)
    class Error(errorMessage: String): MainState(errorMessage = errorMessage)
    class Loading(loadingMessage: String) : MainState(loadingMessage = loadingMessage)
}