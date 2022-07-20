package com.example.dbm.popularmovieskt.domain.service

import com.example.dbm.popularmovieskt.presentation.model.MovieDetailsView
import com.example.dbm.popularmovieskt.presentation.model.MovieGridView

interface IMoviesService {
    suspend fun getListMovies(sortValue: String): List<MovieGridView>
    suspend fun getMovieDetails(): MovieDetailsView
    suspend fun getFavoriteMovies(): List<MovieGridView>
    suspend fun addFavoriteMovie(movieId: Int)
    suspend fun removeFavoriteMovie(movieId: Int)
}