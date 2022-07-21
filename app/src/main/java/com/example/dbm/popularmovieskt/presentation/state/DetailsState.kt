package com.example.dbm.popularmovieskt.presentation.state

import com.example.dbm.popularmovieskt.presentation.model.MovieDetailsView

sealed class DetailsState(
    val value: MovieDetailsView? = null,
    val errorMessage: String = "",
    val loadingMessage: String = ""
) {
    class Success(value: MovieDetailsView?): DetailsState(value)
    class Error(errorMessage: String): DetailsState(errorMessage = errorMessage)
    class Loading(loadingMessage: String) : DetailsState(loadingMessage = loadingMessage)
}