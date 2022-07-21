package com.example.dbm.popularmovieskt.domain.usecase.movies

import com.example.dbm.popularmovieskt.domain.model.MovieDomain
import com.example.dbm.popularmovieskt.domain.repository.IMoviesRepository
import javax.inject.Inject

interface IGetFavoriteMoviesUseCase {
    suspend operator fun invoke(): List<MovieDomain>
}

class GetFavoriteMoviesUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository
): IGetFavoriteMoviesUseCase {

    override suspend fun invoke(): List<MovieDomain> {
        return moviesRepository.getListFavoriteMovies()
    }
}