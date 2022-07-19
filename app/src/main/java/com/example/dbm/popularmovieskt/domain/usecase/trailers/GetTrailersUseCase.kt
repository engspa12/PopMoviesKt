package com.example.dbm.popularmovieskt.domain.usecase.trailers

import com.example.dbm.popularmovieskt.domain.repository.ITrailersRepository
import com.example.dbm.popularmovieskt.domain.util.toView
import com.example.dbm.popularmovieskt.presentation.model.TrailerView
import javax.inject.Inject

interface IGetTrailersUseCase {
    suspend operator fun invoke(movieId: Int): List<TrailerView>
}

class GetTrailersUseCase @Inject constructor(
    private val trailersRepository: ITrailersRepository
): IGetTrailersUseCase {
    override suspend fun invoke(movieId: Int): List<TrailerView> {
        return trailersRepository.getMovieTrailers(movieId).map {
            it.toView()
        }
    }

}