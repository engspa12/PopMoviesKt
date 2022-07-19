package com.example.dbm.popularmovieskt.data.local.mapper

import com.example.dbm.popularmovieskt.data.util.CacheMapper
import com.example.dbm.popularmovieskt.data.local.model.MovieCache
import com.example.dbm.popularmovieskt.domain.model.MovieDomain

class MovieCacheMapper: CacheMapper<MovieCache, MovieDomain> {
    override fun mapToDomainModel(dto: MovieCache): MovieDomain {
        return MovieDomain(
            movieId = dto.movieId,
            movieName = dto.movieName,
            movieReleaseDate = dto.movieReleaseDate,
            movieRating = dto.movieRating,
            moviePosterPath = dto.moviePosterPath,
            movieSynopsis = dto.movieSynopsis,
            movieImage = dto.movieImage
        )
    }

    override fun mapFromDomainModel(domainModel: MovieDomain): MovieCache {
        return MovieCache(
            movieId = domainModel.movieId,
            movieName = domainModel.movieName,
            movieReleaseDate = domainModel.movieReleaseDate,
            movieRating = domainModel.movieRating,
            moviePosterPath = domainModel.moviePosterPath,
            movieSynopsis = domainModel.movieSynopsis,
            movieImage = domainModel.movieImage
        )
    }
}