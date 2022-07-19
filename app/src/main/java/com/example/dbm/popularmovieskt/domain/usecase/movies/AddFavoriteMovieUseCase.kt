package com.example.dbm.popularmovieskt.domain.usecase.movies

import com.example.dbm.popularmovieskt.domain.model.MovieDomain
import com.example.dbm.popularmovieskt.domain.repository.IMoviesRepository
import javax.inject.Inject

interface IAddFavoriteMovieUseCase{
    suspend operator fun invoke(movieItem: MovieDomain)
}

class AddFavoriteMovieUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository
): IAddFavoriteMovieUseCase {

    override suspend fun invoke(movieItem: MovieDomain) {
        moviesRepository.addFavorite(movieItem)
    }

}