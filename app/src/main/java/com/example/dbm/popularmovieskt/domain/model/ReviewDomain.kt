package com.example.dbm.popularmovieskt.domain.model

import java.util.*

data class ReviewDomain(
    val id: String,
    val author: String,
    val content: String,
    val creationDate: String,
    val editionDate: String
)