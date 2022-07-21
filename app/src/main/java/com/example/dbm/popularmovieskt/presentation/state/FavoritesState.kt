package com.example.dbm.popularmovieskt.presentation.state

import com.example.dbm.popularmovieskt.presentation.model.MovieDetailsView

sealed class FavoritesState(
    val value: List<MovieDetailsView>? = null,
    val errorMessage: String = "",
    val loadingMessage: String = ""
) {
    class Success(value: List<MovieDetailsView>): FavoritesState(value)
    class Error(errorMessage: String): FavoritesState(errorMessage = errorMessage)
    class Loading(loadingMessage: String) : FavoritesState(loadingMessage = loadingMessage)
}