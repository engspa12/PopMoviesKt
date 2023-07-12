package com.example.dbm.popularmovieskt.domain.usecase.movies

import com.example.dbm.popularmovieskt.domain.model.MovieDomain
import com.example.dbm.popularmovieskt.domain.repository.IMoviesRepository
import com.example.dbm.popularmovieskt.domain.util.MoviesDomainError
import com.example.dbm.popularmovieskt.global.Constants
import com.example.dbm.popularmovieskt.util.ResultWrapper
import java.io.IOException
import javax.inject.Inject

interface IGetMoviesUseCase {
    suspend operator fun invoke(sortValue: String): ResultWrapper<List<MovieDomain>, MoviesDomainError>
}

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository
) : IGetMoviesUseCase {

    override suspend fun invoke(sortValue: String): ResultWrapper<List<MovieDomain>, MoviesDomainError> {
        val returnedList = mutableListOf<MovieDomain>()
        return getMovies(returnedList, sortValue, Constants.PAGE_INITIAL_VALUE)
    }

    private suspend fun getMovies(
        returnedList: MutableList<MovieDomain>,
        sortValue: String,
        page: Int
    ): ResultWrapper<List<MovieDomain>, MoviesDomainError> {

        when(val result = moviesRepository.getListMovies(sortValue, page)){
            is ResultWrapper.Success -> {
                result.value.map {
                    returnedList.add(it)
                }

                return if(page == Constants.MAX_PAGES){
                    ResultWrapper.Success(returnedList)
                } else {
                    val newPage = page + 1
                    getMovies(returnedList, sortValue, newPage)
                }
            }
            is ResultWrapper.Failure -> {
                return if(result.exception is IOException){
                    ResultWrapper.Failure(error = MoviesDomainError.GENERIC)
                } else {
                    ResultWrapper.Failure(error = MoviesDomainError.UNKNOWN)
                }
            }
        }
    }

}