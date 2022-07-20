package com.example.dbm.popularmovieskt.domain.model

data class MovieDomain(
    val movieId: Int,
    val movieName: String,
    val movieReleaseDate: String,
    val movieRating: Double,
    val moviePosterPath: String,
    val movieSynopsis: String
)