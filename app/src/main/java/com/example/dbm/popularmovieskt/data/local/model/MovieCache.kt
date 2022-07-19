package com.example.dbm.popularmovieskt.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class MovieCache(
    @PrimaryKey
    var id: Int = 0,
    val movieId: Int,
    val movieName: String,
    val movieReleaseDate: String,
    val movieRating: Double,
    val moviePosterPath: String,
    val movieSynopsis: String,
    val movieImage: String
)