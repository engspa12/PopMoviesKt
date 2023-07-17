package com.example.dbm.popularmovieskt.presentation.state

import com.example.dbm.popularmovieskt.presentation.model.MovieDetailsView
import com.example.dbm.popularmovieskt.presentation.util.MoviesViewError

data class DetailsState(
    val movieDetailsView: MovieDetailsView? = null,
    val errorPresent: Boolean = false,
    val isLoading: Boolean = false,
    val errorType: MoviesViewError = MoviesViewError.NONE
)