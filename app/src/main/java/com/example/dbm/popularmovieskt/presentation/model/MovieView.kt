package com.example.dbm.popularmovieskt.presentation.model

data class MovieView(
    val movieId: Int,
    val movieName: String,
    val movieReleaseDate: String,
    val movieRating: Double,
    val moviePosterPath: String,
    val movieSynopsis: String,
    val movieImage: String
)
