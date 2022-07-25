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
import com.example.dbm.popularmovieskt.util.ResultWrapper
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

    override suspend fun getListMovies(sortValue: String): ResultWrapper<List<MovieGridView>> {

        return if(sortValue != Constants.SORT_BY_FAVORITE_MOVIES){

            when(val result = getMoviesUseCase(sortValue)) {
                is ResultWrapper.Success -> {
                    innerListMovies = result.value
                    val listMovies = result.value.map {
                        it.toGridView()
                    }
                    ResultWrapper.Success(listMovies)
                }
                is ResultWrapper.Failure -> {
                    ResultWrapper.Failure(result.errorMessage)
                }
            }
        } else {
            getFavoriteMovies()
        }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsView {
        val movie = findMovieByIdInInnerList(movieId)

        val trailers = getTrailersUseCase(movieId).map {
            it.toView()
        }

        val reviews = getReviewsUseCase(movieId).map {
            it.toView()
        }

        return movie.toDetailsView(
            trailers = trailers,
            reviews = reviews,
            isFavorite = checkIfMovieIsFavorite(movieId)
        )
    }

    override suspend fun handleFavoriteMovieEdition(movieId: Int) {
        val favoriteMovies = getFavoriteMoviesUseCase()

        val result = favoriteMovies.filter { it.movieId == movieId }

        if(result.isEmpty()){
            addFavoriteMovie(movieId)
        } else if (result.size == 1) {
            removeFavoriteMovie(movieId)
        } else {
            throw RuntimeException("Data is corrupted, there are two or more favorite movies with the same ID")
        }
    }

    private suspend fun checkIfMovieIsFavorite(movieId: Int): Boolean{
        val favoriteMovies = getFavoriteMoviesUseCase()
        val isFavorite = favoriteMovies.filter { it.movieId == movieId }
        return (isFavorite.size == 1)
    }

    private suspend fun getFavoriteMovies(): ResultWrapper<List<MovieGridView>> {
        val favoriteMovies = getFavoriteMoviesUseCase()

        innerListMovies = favoriteMovies

        return if(favoriteMovies.isNotEmpty()){
            ResultWrapper.Success(
                favoriteMovies.map {
                    it.toGridView()
                }
            )
        } else {
            ResultWrapper.Failure("The list of favorite movies is empty")
        }
    }

    private suspend fun addFavoriteMovie(movieId: Int) {
        val movie = findMovieByIdInInnerList(movieId)
        addFavoriteMovieUseCase(movie)
    }

    private suspend fun removeFavoriteMovie(movieId: Int) {
        removeFavoriteMovieUseCase(movieId)
    }

    private fun findMovieByIdInInnerList(movieId: Int): MovieDomain {
        val matches = innerListMovies.filter { it.movieId == movieId }
        if(matches.isNotEmpty()){
            return matches[0]
        } else {
            throw RuntimeException("No movieId was found")
        }
    }
}