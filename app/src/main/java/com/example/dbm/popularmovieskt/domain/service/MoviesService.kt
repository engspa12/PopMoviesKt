package com.example.dbm.popularmovieskt.domain.service

import com.example.dbm.popularmovieskt.domain.model.MovieDomain
import com.example.dbm.popularmovieskt.domain.usecase.movies.IAddFavoriteMovieUseCase
import com.example.dbm.popularmovieskt.domain.usecase.movies.IGetFavoriteMoviesUseCase
import com.example.dbm.popularmovieskt.domain.usecase.movies.IGetMoviesUseCase
import com.example.dbm.popularmovieskt.domain.usecase.movies.IRemoveFavoriteMovieUseCase
import com.example.dbm.popularmovieskt.domain.usecase.reviews.IGetReviewsUseCase
import com.example.dbm.popularmovieskt.domain.usecase.trailers.IGetTrailersUseCase
import com.example.dbm.popularmovieskt.domain.util.toDetailsView
import com.example.dbm.popularmovieskt.domain.util.toGridView
import com.example.dbm.popularmovieskt.domain.util.toView
import com.example.dbm.popularmovieskt.global.Constants
import com.example.dbm.popularmovieskt.presentation.model.MovieDetailsView
import com.example.dbm.popularmovieskt.presentation.model.MovieGridView
import javax.inject.Inject

class MoviesService @Inject constructor(
    private val getMoviesUseCase: IGetMoviesUseCase,
    private val getFavoriteMoviesUseCase: IGetFavoriteMoviesUseCase,
    private val addFavoriteMovieUseCase: IAddFavoriteMovieUseCase,
    private val removeFavoriteMovieUseCase: IRemoveFavoriteMovieUseCase,
    private val getTrailersUseCase: IGetTrailersUseCase,
    private val getReviewsUseCase: IGetReviewsUseCase
): IMoviesService {

    private var innerListMovies: List<MovieDomain> = emptyList()

    override suspend fun getListMovies(sortValue: String): List<MovieGridView> {

        return if(sortValue != Constants.SORT_BY_FAVORITE_MOVIES){
            val moviesList = getMoviesUseCase(sortValue)
            innerListMovies = moviesList

            moviesList.map {
                it.toGridView()
            }
        } else {
            getFavoriteMovies()
        }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsView {
        //val movie = findMovieById(movieId)
        val temp = getListMovies(Constants.SORT_BY_POPULAR)
        val movie = innerListMovies.filter { it.movieId == movieId }

        val trailers = getTrailersUseCase(movieId).map {
            it.toView()
        }

        val reviews = getReviewsUseCase(movieId).map {
            it.toView()
        }

        val favoriteMovies = getFavoriteMoviesUseCase()

        val isFavorite = favoriteMovies.filter { it.movieId == movieId }

        return movie[0].toDetailsView(
            trailers = trailers,
            reviews = reviews,
            isFavorite = (isFavorite.size == 1)
        )
    }

    override suspend fun getFavoriteMovies(): List<MovieGridView> {
        val favoriteMovies = getFavoriteMoviesUseCase()

        return favoriteMovies.map {
            it.toGridView()
        }
    }

    override suspend fun addFavoriteMovie(movieId: Int) {
        val movie = findMovieById(movieId)
        addFavoriteMovieUseCase(movie)
    }

    override suspend fun removeFavoriteMovie(movieId: Int) {
        removeFavoriteMovieUseCase(movieId)
    }

    private fun findMovieById(movieId: Int): MovieDomain {
        val matches = innerListMovies.filter { it.movieId == movieId }
        if(matches.isNotEmpty()){
            return matches[0]
        } else {
            throw RuntimeException("No movieId was found")
        }
    }
}