package com.example.dbm.popularmovieskt.presentation.state

import com.example.dbm.popularmovieskt.presentation.model.MovieDetailsView
import com.example.dbm.popularmovieskt.util.StringWrapper

sealed class DetailsState(
    val value: MovieDetailsView? = null,
    val errorMessage: StringWrapper = StringWrapper.SimpleStringWrapper(""),
    val loadingMessage: StringWrapper = StringWrapper.SimpleStringWrapper("")
) {
    class Success(value: MovieDetailsView?): DetailsState(value)
    class Error(errorMessage: StringWrapper): DetailsState(errorMessage = errorMessage)
    class Loading(loadingMessage: StringWrapper) : DetailsState(loadingMessage = loadingMessage)
}