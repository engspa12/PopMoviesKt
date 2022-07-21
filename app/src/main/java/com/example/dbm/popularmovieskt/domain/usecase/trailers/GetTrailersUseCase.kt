package com.example.dbm.popularmovieskt.domain.usecase.trailers

import com.example.dbm.popularmovieskt.domain.model.TrailerDomain
import com.example.dbm.popularmovieskt.domain.repository.ITrailersRepository
import javax.inject.Inject

interface IGetTrailersUseCase {
    suspend operator fun invoke(movieId: Int): List<TrailerDomain>
}

class GetTrailersUseCase @Inject constructor(
    private val trailersRepository: ITrailersRepository
): IGetTrailersUseCase {

    override suspend fun invoke(movieId: Int): List<TrailerDomain> {
        return trailersRepository.getMovieTrailers(movieId)
    }

}