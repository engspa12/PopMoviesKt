package com.example.dbm.popularmovieskt.di

import com.example.dbm.popularmovieskt.data.local.mapper.MovieCacheMapper
import com.example.dbm.popularmovieskt.data.local.model.MovieCache
import com.example.dbm.popularmovieskt.data.network.mapper.MovieNetworkMapper
import com.example.dbm.popularmovieskt.data.network.mapper.ReviewNetworkMapper
import com.example.dbm.popularmovieskt.data.network.mapper.TrailerNetworkMapper
import com.example.dbm.popularmovieskt.data.network.model.MovieNetwork
import com.example.dbm.popularmovieskt.data.network.model.ReviewNetwork
import com.example.dbm.popularmovieskt.data.network.model.TrailerNetwork
import com.example.dbm.popularmovieskt.domain.model.MovieDomain
import com.example.dbm.popularmovieskt.domain.model.ReviewDomain
import com.example.dbm.popularmovieskt.domain.model.TrailerDomain
import com.example.dbm.popularmovieskt.domain.util.CacheMapper
import com.example.dbm.popularmovieskt.domain.util.NetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    fun provideMovieNetworkMapper(): NetworkMapper<MovieNetwork, MovieDomain> {
        return MovieNetworkMapper()
    }

    @Provides
    fun provideMovieCacheMapper(): CacheMapper<MovieCache, MovieDomain> {
        return MovieCacheMapper()
    }

    @Provides
    fun provideTrailerNetworkMapper(): NetworkMapper<TrailerNetwork, TrailerDomain> {
        return TrailerNetworkMapper()
    }

    @Provides
    fun provideReviewNetworkMapper(): NetworkMapper<ReviewNetwork, ReviewDomain> {
        return ReviewNetworkMapper()
    }

}