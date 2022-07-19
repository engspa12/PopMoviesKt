package com.example.dbm.popularmovieskt.di

import com.example.dbm.popularmovieskt.data.local.datasource.MoviesDao
import com.example.dbm.popularmovieskt.data.local.model.MovieCache
import com.example.dbm.popularmovieskt.data.network.datasource.ServiceAPI
import com.example.dbm.popularmovieskt.data.network.model.MovieNetwork
import com.example.dbm.popularmovieskt.data.network.model.ReviewNetwork
import com.example.dbm.popularmovieskt.data.network.model.TrailerNetwork
import com.example.dbm.popularmovieskt.data.repository.MoviesRepository
import com.example.dbm.popularmovieskt.data.repository.ReviewsRepository
import com.example.dbm.popularmovieskt.data.repository.TrailersRepository
import com.example.dbm.popularmovieskt.data.util.CacheMapper
import com.example.dbm.popularmovieskt.data.util.NetworkMapper
import com.example.dbm.popularmovieskt.domain.model.MovieDomain
import com.example.dbm.popularmovieskt.domain.model.ReviewDomain
import com.example.dbm.popularmovieskt.domain.model.TrailerDomain
import com.example.dbm.popularmovieskt.domain.repository.IMoviesRepository
import com.example.dbm.popularmovieskt.domain.repository.IReviewsRepository
import com.example.dbm.popularmovieskt.domain.repository.ITrailersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    fun provideMoviesRepository(
        networkDataSource: ServiceAPI,
        localDataSource: MoviesDao,
        networkMapper: NetworkMapper<MovieNetwork, MovieDomain>,
        cacheMapper: CacheMapper<MovieCache, MovieDomain>,
        @DispatchersModule.IODispatcher dispatcher: CoroutineDispatcher
    ): IMoviesRepository {
        return MoviesRepository(networkDataSource, localDataSource, networkMapper, cacheMapper, dispatcher)
    }

    @Provides
    fun provideTrailersRepository(
        networkDataSource: ServiceAPI,
        networkMapper: NetworkMapper<TrailerNetwork, TrailerDomain>,
        @DispatchersModule.IODispatcher dispatcher: CoroutineDispatcher
    ): ITrailersRepository {
        return TrailersRepository(networkDataSource, networkMapper, dispatcher)
    }

    @Provides
    fun provideReviewsRepository(
        networkDataSource: ServiceAPI,
        networkMapper: NetworkMapper<ReviewNetwork, ReviewDomain>,
        @DispatchersModule.IODispatcher dispatcher: CoroutineDispatcher
    ): IReviewsRepository {
        return ReviewsRepository(networkDataSource, networkMapper, dispatcher)
    }

}