package com.example.dbm.popularmovieskt.data.network.mapper

import com.example.dbm.popularmovieskt.data.network.model.MovieNetwork
import com.example.dbm.popularmovieskt.domain.model.MovieDomain
import com.example.dbm.popularmovieskt.domain.util.NetworkMapper
import com.example.dbm.popularmovieskt.global.Constants

class MovieNetworkMapper: NetworkMapper<MovieNetwork, MovieDomain> {
    override fun mapToDomainModel(dto: MovieNetwork): MovieDomain {
        return MovieDomain(
            movieId = dto.id ?: 1,
            movieName = dto.title ?: "No title",
            movieReleaseDate = dto.releaseDate ?: "No release date",
            movieRating = dto.rating ?: 0.0,
            moviePosterPath = Constants.BASE_POSTER_URL + (dto.posterPath ?: "/"),
            movieSynopsis = dto.synopsis ?: "No synopsis"
        )
    }
}