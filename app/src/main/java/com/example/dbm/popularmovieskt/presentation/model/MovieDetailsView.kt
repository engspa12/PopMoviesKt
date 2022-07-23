package com.example.dbm.popularmovieskt.presentation.model

data class MovieDetailsView(
    val movieId: Int,
    val movieName: String,
    val movieReleaseDate: String,
    val movieRating: Double,
    val moviePosterPath: String,
    val movieSynopsis: String,
    val trailers: List<TrailerView>?,
    val reviews: List<ReviewView>?,
    val isFavorite: Boolean
)
