package com.example.dbm.popularmovieskt.di

import com.example.dbm.popularmovieskt.domain.repository.IMoviesRepository
import com.example.dbm.popularmovieskt.domain.repository.IReviewsRepository
import com.example.dbm.popularmovieskt.domain.repository.ITrailersRepository
import com.example.dbm.popularmovieskt.domain.usecase.movies.*
import com.example.dbm.popularmovieskt.domain.usecase.reviews.GetReviewsUseCase
import com.example.dbm.popularmovieskt.domain.usecase.reviews.IGetReviewsUseCase
import com.example.dbm.popularmovieskt.domain.usecase.trailers.GetTrailersUseCase
import com.example.dbm.popularmovieskt.domain.usecase.trailers.IGetTrailersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun provideGetMoviesUseCase(
        moviesRepository: IMoviesRepository
    ): IGetMoviesUseCase {
        return GetMoviesUseCase(moviesRepository)
    }

    @Provides
    fun provideAddFavoriteMovieUseCase(
        moviesRepository: IMoviesRepository
    ): IAddFavoriteMovieUseCase {
        return AddFavoriteMovieUseCase(moviesRepository)
    }

    @Provides
    fun provideRemoveFavoriteMovieUseCase(
        moviesRepository: IMoviesRepository
    ): IRemoveFavoriteMovieUseCase {
        return RemoveFavoriteMovieUseCase(moviesRepository)
    }

    @Provides
    fun provideGetFavoriteMoviesUseCase(
        moviesRepository: IMoviesRepository
    ): IGetFavoriteMoviesUseCase{
        return GetFavoriteMoviesUseCase(moviesRepository)
    }

    @Provides
    fun provideGetReviewsUseCase(
        reviewsRepository: IReviewsRepository
    ): IGetReviewsUseCase{
        return GetReviewsUseCase(reviewsRepository)
    }

    @Provides
    fun provideGetTrailersUseCase(
        trailersRepository: ITrailersRepository
    ): IGetTrailersUseCase {
        return GetTrailersUseCase(trailersRepository)
    }

}