package com.example.dbm.popularmovieskt.domain.service

import com.example.dbm.popularmovieskt.presentation.model.MovieDetailsView
import com.example.dbm.popularmovieskt.presentation.model.MovieGridView
import com.example.dbm.popularmovieskt.util.ResultWrapper

interface IMoviesService {
    suspend fun getListMovies(sortValue: String): ResultWrapper<List<MovieGridView>>
    suspend fun getMovieDetails(movieId: Int): MovieDetailsView
    suspend fun handleFavoriteMovieEdition(movieId: Int)
}