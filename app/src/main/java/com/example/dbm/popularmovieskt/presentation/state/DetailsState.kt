package com.example.dbm.popularmovieskt.presentation.state

import com.example.dbm.popularmovieskt.presentation.model.MovieDetailsView
import com.example.dbm.popularmovieskt.presentation.model.ReviewView
import com.example.dbm.popularmovieskt.presentation.model.TrailerView

sealed class DetailsState(
    val movie: MovieDetailsView? = null,
    val trailers: List<TrailerView>? = null,
    val reviews: List<ReviewView>? = null,
    val errorMessage: String = "",
    val loadingMessage: String = ""
) {
    class Success(movie: MovieDetailsView?, trailers: List<TrailerView>?, reviews: List<ReviewView>?): DetailsState(movie = movie, trailers =  trailers, reviews = reviews)
    class Error(errorMessage: String): DetailsState(errorMessage = errorMessage)
    class Loading(loadingMessage: String) : DetailsState(loadingMessage = loadingMessage)
}