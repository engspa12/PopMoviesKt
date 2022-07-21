package com.example.dbm.popularmovieskt.presentation.state

import com.example.dbm.popularmovieskt.presentation.model.MovieGridView

sealed class MainState(
    val value: List<MovieGridView>? = null,
    val errorMessage: String = "",
    val loadingMessage: String = ""
) {
    class Success(value: List<MovieGridView>): MainState(value)
    class Error(errorMessage: String): MainState(errorMessage = errorMessage)
    class Loading(loadingMessage: String) : MainState(loadingMessage = loadingMessage)
}