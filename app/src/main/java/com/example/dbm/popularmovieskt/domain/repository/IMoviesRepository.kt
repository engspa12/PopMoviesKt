package com.example.dbm.popularmovieskt.domain.repository

import com.example.dbm.popularmovieskt.domain.model.MovieDomain

interface IMoviesRepository {
    suspend fun getListMovies(sortValue: String, page: Int): List<MovieDomain>
    suspend fun getListFavoriteMovies(): List<MovieDomain>
    suspend fun addFavorite(movieItem: MovieDomain)
    suspend fun removeFavorite(movieId: Int)
}