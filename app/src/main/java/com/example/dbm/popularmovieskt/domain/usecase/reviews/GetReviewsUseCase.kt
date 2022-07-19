package com.example.dbm.popularmovieskt.domain.usecase.reviews

import com.example.dbm.popularmovieskt.domain.repository.IReviewsRepository
import com.example.dbm.popularmovieskt.domain.util.toView
import com.example.dbm.popularmovieskt.presentation.model.ReviewView
import com.example.dbm.popularmovieskt.presentation.model.TrailerView
import javax.inject.Inject

interface IGetReviewsUseCase {
    suspend operator fun invoke(movieId: Int): List<ReviewView>
}

class GetReviewsUseCase @Inject constructor(
    private val reviewsRepository: IReviewsRepository
): IGetReviewsUseCase {
    override suspend fun invoke(movieId: Int): List<ReviewView> {
        return reviewsRepository.getMovieReviews(movieId).map {
            it.toView()
        }
    }

}