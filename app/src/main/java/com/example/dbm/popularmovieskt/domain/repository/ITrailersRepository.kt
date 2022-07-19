package com.example.dbm.popularmovieskt.domain.repository

import com.example.dbm.popularmovieskt.domain.model.MovieDomain
import com.example.dbm.popularmovieskt.domain.model.TrailerDomain

interface ITrailersRepository {
    suspend fun getMovieTrailers(movieId: Int): List<TrailerDomain>
}