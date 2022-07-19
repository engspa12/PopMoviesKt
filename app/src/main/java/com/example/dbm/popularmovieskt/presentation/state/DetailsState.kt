package com.example.dbm.popularmovieskt.presentation.state

import com.example.dbm.popularmovieskt.presentation.model.MovieView

sealed class DetailsState(
    val value: MovieView? = null,
    val errorMessage: String = "",
    val loadingMessage: String = ""
) {
    class Success(value: MovieView?): DetailsState(value)
    class Error(errorMessage: String): DetailsState(errorMessage = errorMessage)
    class Loading(loadingMessage: String) : DetailsState(loadingMessage = loadingMessage)
}