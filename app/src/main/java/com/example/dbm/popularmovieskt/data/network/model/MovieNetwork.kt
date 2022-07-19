package com.example.dbm.popularmovieskt.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieNetwork(
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "title")
    var title: String? = null,
    @Json(name = "overview")
    var synopsis: String? = null,
    @Json(name = "vote_average")
    var rating: Double? = null,
    @Json(name = "release_date")
    var releaseDate: String? = null,
    @Json(name = "poster_path")
    var posterPath: String? = null
)