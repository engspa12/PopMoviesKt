package com.example.dbm.popularmovieskt.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewNetwork(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "author")
    var author: String? = null,
    @Json(name = "content")
    var content: String? = null,
    @Json(name ="created_at")
    val creationDate: String? = null,
    @Json(name = "updated_at")
    val editionDate: String? = null
)
