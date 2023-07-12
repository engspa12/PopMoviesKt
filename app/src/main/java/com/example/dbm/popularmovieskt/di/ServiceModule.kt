package com.example.dbm.popularmovieskt.di

import com.example.dbm.popularmovieskt.domain.service.IMoviesService
import com.example.dbm.popularmovieskt.domain.service.MoviesService
import com.example.dbm.popularmovieskt.domain.usecase.movies.IAddFavoriteMovieUseCase
import com.example.dbm.popularmovieskt.domain.usecase.movies.IGetFavoriteMoviesUseCase
import com.example.dbm.popularmovieskt.domain.usecase.movies.IGetMoviesUseCase
import com.example.dbm.popularmovieskt.domain.usecase.movies.IRemoveFavoriteMovieUseCase
import com.example.dbm.popularmovieskt.domain.usecase.reviews.IGetReviewsUseCase
import com.example.dbm.popularmovieskt.domain.usecase.trailers.IGetTrailersUseCase
import com.example.dbm.popularmovieskt.domain.util.IConnectionChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideMoviesService(
        getMoviesUseCase: IGetMoviesUseCase,
        getFavoriteMoviesUseCase: IGetFavoriteMoviesUseCase,
        addFavoriteMovieUseCase: IAddFavoriteMovieUseCase,
        removeFavoriteMovieUseCase: IRemoveFavoriteMovieUseCase,
        getTrailersUseCase: IGetTrailersUseCase,
        getReviewsUseCase: IGetReviewsUseCase,
        connectionChecker: IConnectionChecker
    ): IMoviesService {
        return MoviesService(
            getMoviesUseCase,
            getFavoriteMoviesUseCase,
            addFavoriteMovieUseCase,
            removeFavoriteMovieUseCase,
            getTrailersUseCase,
            getReviewsUseCase,
            connectionChecker
        )
    }
}