package com.example.dbm.popularmovieskt.domain.service

import com.example.dbm.popularmovieskt.domain.model.MovieDomain
import com.example.dbm.popularmovieskt.domain.model.TrailerDomain
import com.example.dbm.popularmovieskt.domain.usecase.movies.IAddFavoriteMovieUseCase
import com.example.dbm.popularmovieskt.domain.usecase.movies.IGetFavoriteMoviesUseCase
import com.example.dbm.popularmovieskt.domain.usecase.movies.IGetMoviesUseCase
import com.example.dbm.popularmovieskt.domain.usecase.movies.IRemoveFavoriteMovieUseCase
import com.example.dbm.popularmovieskt.domain.usecase.reviews.IGetReviewsUseCase
import com.example.dbm.popularmovieskt.domain.usecase.trailers.IGetTrailersUseCase
import com.example.dbm.popularmovieskt.presentation.model.MovieDetailsView
import com.example.dbm.popularmovieskt.presentation.model.MovieGridView
import com.example.dbm.popularmovieskt.presentation.model.ReviewView
import javax.inject.Inject

class MoviesService @Inject constructor(
    private val getMoviesUseCase: IGetMoviesUseCase,
    private val getFavoriteMoviesUseCase: IGetFavoriteMoviesUseCase,
    private val addFavoriteMovieUseCase: IAddFavoriteMovieUseCase,
    private val removeFavoriteMovieUseCase: IRemoveFavoriteMovieUseCase,
    private val getTrailersUseCase: IGetTrailersUseCase,
    private val getReviewsUseCase: IGetReviewsUseCase
) {

    private var listMovies: List<MovieDomain> = emptyList()
    private var listTrailers: List<TrailerDomain> = emptyList()
    private var listReviews: List<ReviewView> = emptyList()

    /*override suspend fun getListMovies(sortValue: String): List<MovieGridView> {
        val moviesList = getMoviesUseCase(sortValue)
    }

    override suspend fun getMovieDetails(): MovieDetailsView {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteMovies(): List<MovieGridView> {
        TODO("Not yet implemented")
    }

    override suspend fun addFavoriteMovie(movieId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun removeFavoriteMovie(movieId: Int) {
        TODO("Not yet implemented")
    }*/
}