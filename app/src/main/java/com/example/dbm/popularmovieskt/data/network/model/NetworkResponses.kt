package com.example.dbm.popularmovieskt.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewsResponse(
    @Json(name = "total_results")
    var totalReviews: Int? = null,
    @Json(name = "results")
    var reviews: List<ReviewNetwork> = arrayListOf()
)

@JsonClass(generateAdapter = true)
data class TrailersResponse(
    @Json(name = "results")
    var trailers: List<TrailerNetwork> = arrayListOf()
)

@JsonClass(generateAdapter = true)
data class MoviesResponse(
    @Json(name = "results")
    var movies: List<MovieNetwork> = arrayListOf()
)


