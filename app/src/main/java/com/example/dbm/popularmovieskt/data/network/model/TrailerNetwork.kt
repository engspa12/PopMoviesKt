package com.example.dbm.popularmovieskt.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrailerNetwork(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "key")
    var key: String? = null,
    @Json(name = "type")
    var type: String? = null
)