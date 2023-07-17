package com.example.dbm.popularmovieskt.presentation.state

import com.example.dbm.popularmovieskt.presentation.model.MovieGridView
import com.example.dbm.popularmovieskt.presentation.util.MoviesViewError

data class MainState(
    val listMoviesGrid: List<MovieGridView>? = null,
    val errorPresent: Boolean = false,
    val isLoading: Boolean = false,
    val errorType: MoviesViewError = MoviesViewError.NONE
)