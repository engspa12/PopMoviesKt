package com.example.dbm.popularmovieskt.data.repository

import com.example.dbm.popularmovieskt.data.local.datasource.MoviesDao
import com.example.dbm.popularmovieskt.data.local.model.MovieCache
import com.example.dbm.popularmovieskt.data.network.datasource.ServiceAPI
import com.example.dbm.popularmovieskt.data.network.model.MovieNetwork
import com.example.dbm.popularmovieskt.di.DispatchersModule
import com.example.dbm.popularmovieskt.domain.model.MovieDomain
import com.example.dbm.popularmovieskt.domain.repository.IMoviesRepository
import com.example.dbm.popularmovieskt.domain.util.CacheMapper
import com.example.dbm.popularmovieskt.domain.util.NetworkMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val networkDataSource: ServiceAPI,
    private val localDataSource: MoviesDao,
    private val networkMapper: NetworkMapper<MovieNetwork, MovieDomain>,
    private val cacheMapper: CacheMapper<MovieCache, MovieDomain>,
    @DispatchersModule.IODispatcher private val coroutineDispatcher: CoroutineDispatcher
): IMoviesRepository {

    override suspend fun getListMovies(sortValue: String, page: Int): List<MovieDomain> {
        return withContext(coroutineDispatcher){
            val networkResponse = networkDataSource.getMovies(sortValue = sortValue, page = page)
            networkResponse.movies.map {
                networkMapper.mapToDomainModel(it)
            }
        }
    }

    override suspend fun getListFavoriteMovies(): List<MovieDomain> {
        return withContext(coroutineDispatcher){
            localDataSource.getFavoriteMovies().map {
                cacheMapper.mapToDomainModel(it)
            }
        }
    }

    override suspend fun addFavorite(movieItem: MovieDomain) {
        withContext(coroutineDispatcher){
            val cachedMovie = cacheMapper.mapFromDomainModel(movieItem)
            localDataSource.saveFavoriteMovie(cachedMovie)
        }
    }

    override suspend fun removeFavorite(movieId: Int) {
        withContext(coroutineDispatcher){
            localDataSource.deleteFavoriteMovie(movieId)
        }
    }
}