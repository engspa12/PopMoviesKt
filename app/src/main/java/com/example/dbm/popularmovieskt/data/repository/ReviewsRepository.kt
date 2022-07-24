package com.example.dbm.popularmovieskt.data.repository

import com.example.dbm.popularmovieskt.data.network.datasource.ServiceAPI
import com.example.dbm.popularmovieskt.data.network.model.ReviewNetwork
import com.example.dbm.popularmovieskt.di.DispatchersModule
import com.example.dbm.popularmovieskt.domain.model.ReviewDomain
import com.example.dbm.popularmovieskt.domain.repository.IReviewsRepository
import com.example.dbm.popularmovieskt.domain.util.NetworkMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReviewsRepository @Inject constructor(
    private val networkDataSource: ServiceAPI,
    private val networkMapper: NetworkMapper<ReviewNetwork, ReviewDomain>,
    @DispatchersModule.IODispatcher private val coroutineDispatcher: CoroutineDispatcher
): IReviewsRepository {

    override suspend fun getMovieReviews(movieId: Int): List<ReviewDomain> {
        return withContext(coroutineDispatcher) {
            val networkResponse = networkDataSource.getReviews(movieId = movieId)
            networkResponse.reviews.map {
                networkMapper.mapToDomainModel(it)
            }
        }
    }

}