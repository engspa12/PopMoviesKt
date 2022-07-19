package com.example.dbm.popularmovieskt.domain.usecase.movies

import com.example.dbm.popularmovieskt.domain.repository.IMoviesRepository
import javax.inject.Inject

interface IRemoveFavoriteMovieUseCase {
    suspend operator fun invoke(movieId: Int)
}

class RemoveFavoriteMovieUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository
): IRemoveFavoriteMovieUseCase {

    override suspend fun invoke(movieId: Int) {
        moviesRepository.removeFavorite(movieId)
    }

}