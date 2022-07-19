package com.example.dbm.popularmovieskt.data.repository

import com.example.dbm.popularmovieskt.data.network.datasource.ServiceAPI
import com.example.dbm.popularmovieskt.data.network.model.TrailerNetwork
import com.example.dbm.popularmovieskt.data.util.NetworkMapper
import com.example.dbm.popularmovieskt.di.DispatchersModule
import com.example.dbm.popularmovieskt.domain.model.TrailerDomain
import com.example.dbm.popularmovieskt.domain.repository.ITrailersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrailersRepository @Inject constructor(
    private val networkDataSource: ServiceAPI,
    private val networkMapper: NetworkMapper<TrailerNetwork, TrailerDomain>,
    @DispatchersModule.IODispatcher private val coroutineDispatcher: CoroutineDispatcher
): ITrailersRepository {

    override suspend fun getMovieTrailers(movieId: Int): List<TrailerDomain> {
        return withContext(coroutineDispatcher) {
            val networkResponse = networkDataSource.getTrailers(movieId = movieId)
            networkResponse.trailers.map {
                networkMapper.mapToDomainModel(it)
            }
        }
    }
}