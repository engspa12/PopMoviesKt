package com.example.dbm.popularmovieskt.data.repository

import com.example.dbm.popularmovieskt.data.network.datasource.ServiceAPI
import com.example.dbm.popularmovieskt.data.network.model.TrailerNetwork
import com.example.dbm.popularmovieskt.di.DispatchersModule
import com.example.dbm.popularmovieskt.domain.model.TrailerDomain
import com.example.dbm.popularmovieskt.domain.repository.ITrailersRepository
import com.example.dbm.popularmovieskt.domain.util.NetworkMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class TrailersRepository @Inject constructor(
    private val networkDataSource: ServiceAPI,
    private val networkMapper: NetworkMapper<TrailerNetwork, TrailerDomain>,
    @DispatchersModule.IODispatcher private val coroutineDispatcher: CoroutineDispatcher
): ITrailersRepository {

    override suspend fun getMovieTrailers(movieId: Int): List<TrailerDomain> {
        return withContext(coroutineDispatcher) {
            try {
                val networkResponse = networkDataSource.getTrailers(movieId = movieId)
                networkResponse.trailers.map {
                    networkMapper.mapToDomainModel(it)
                }
            } catch (e: IOException) {
                emptyList()
            }
        }
    }
}