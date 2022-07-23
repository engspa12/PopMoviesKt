package com.example.dbm.popularmovieskt.domain.util

import com.example.dbm.popularmovieskt.domain.model.MovieDomain
import com.example.dbm.popularmovieskt.domain.model.ReviewDomain
import com.example.dbm.popularmovieskt.domain.model.TrailerDomain
import com.example.dbm.popularmovieskt.presentation.model.MovieDetailsView
import com.example.dbm.popularmovieskt.presentation.model.MovieGridView
import com.example.dbm.popularmovieskt.presentation.model.ReviewView
import com.example.dbm.popularmovieskt.presentation.model.TrailerView

fun MovieDomain.toGridView(): MovieGridView {
    return MovieGridView(
        this.movieId,
        this.moviePosterPath
    )
}

fun MovieDomain.toDetailsView(
    trailers: List<TrailerView>? = null,
    reviews: List<ReviewView>? = null,
    isFavorite: Boolean
): MovieDetailsView {
    return MovieDetailsView(
        this.movieId,
        this.movieName,
        this.movieReleaseDate,
        this.movieRating,
        this.moviePosterPath,
        this.movieSynopsis,
        trailers,
        reviews,
        isFavorite
    )
}

fun TrailerDomain.toView(): TrailerView {
    return TrailerView(
        this.id,
        this.name,
        this.key,
        this.type
    )
}

fun ReviewDomain.toView(): ReviewView {
    return ReviewView(
        this.id,
        this.author,
        this.content,
        this.creationDate,
        this.editionDate
    )
}