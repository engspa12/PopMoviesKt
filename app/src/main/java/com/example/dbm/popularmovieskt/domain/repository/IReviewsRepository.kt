package com.example.dbm.popularmovieskt.domain.repository

import com.example.dbm.popularmovieskt.domain.model.ReviewDomain

interface IReviewsRepository {
    suspend fun getMovieReviews(movieId: Int): List<ReviewDomain>
}