package com.example.dbm.popularmovieskt.domain.repository

import com.example.dbm.popularmovieskt.domain.model.MovieDomain
import com.example.dbm.popularmovieskt.util.ResultWrapper

interface IMoviesRepository {
    suspend fun getListMovies(sortValue: String, page: Int): ResultWrapper<List<MovieDomain>, Nothing>
    suspend fun getListFavoriteMovies(): List<MovieDomain>
    suspend fun addFavorite(movieItem: MovieDomain)
    suspend fun removeFavorite(movieId: Int)
}