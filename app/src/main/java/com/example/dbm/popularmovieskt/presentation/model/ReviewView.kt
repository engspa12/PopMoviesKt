package com.example.dbm.popularmovieskt.presentation.model

data class ReviewView(
    val id: String,
    val author: String,
    val content: String,
    val creationDate: String,
    val editionDate: String
)