package com.example.dbm.popularmovieskt.presentation.state

import com.example.dbm.popularmovieskt.presentation.model.MovieGridView

sealed class FavoritesState(
    val value: List<MovieGridView>? = null,
    val errorMessage: String = "",
    val loadingMessage: String = ""
) {
    class Success(value: List<MovieGridView>): FavoritesState(value)
    class Error(errorMessage: String): FavoritesState(errorMessage = errorMessage)
    class Loading(loadingMessage: String) : FavoritesState(loadingMessage = loadingMessage)
}