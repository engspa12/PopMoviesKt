package com.example.dbm.popularmovieskt.domain.usecase.movies

import com.example.dbm.popularmovieskt.domain.repository.IMoviesRepository
import com.example.dbm.popularmovieskt.domain.util.toView
import com.example.dbm.popularmovieskt.presentation.model.MovieView
import javax.inject.Inject

interface IGetFavoriteMoviesUseCase {
    suspend operator fun invoke(): List<MovieView>
}

class GetFavoriteMoviesUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository
): IGetFavoriteMoviesUseCase {
    override suspend fun invoke(): List<MovieView> {
        return moviesRepository.getListFavoriteMovies().map {
            it.toView()
        }
    }
}