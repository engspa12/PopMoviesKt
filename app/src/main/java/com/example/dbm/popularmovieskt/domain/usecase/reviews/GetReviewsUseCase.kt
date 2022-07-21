package com.example.dbm.popularmovieskt.domain.usecase.reviews

import com.example.dbm.popularmovieskt.domain.model.ReviewDomain
import com.example.dbm.popularmovieskt.domain.repository.IReviewsRepository
import javax.inject.Inject

interface IGetReviewsUseCase {
    suspend operator fun invoke(movieId: Int): List<ReviewDomain>
}

class GetReviewsUseCase @Inject constructor(
    private val reviewsRepository: IReviewsRepository
): IGetReviewsUseCase {

    override suspend fun invoke(movieId: Int): List<ReviewDomain> {
        return reviewsRepository.getMovieReviews(movieId)
    }

}