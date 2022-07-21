package com.example.dbm.popularmovieskt.domain.usecase.movies

import com.example.dbm.popularmovieskt.domain.model.MovieDomain
import com.example.dbm.popularmovieskt.domain.repository.IMoviesRepository
import com.example.dbm.popularmovieskt.global.Constants
import javax.inject.Inject

interface IGetMoviesUseCase {
    suspend operator fun invoke(sortValue: String): List<MovieDomain>
}

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository
) : IGetMoviesUseCase {

    override suspend fun invoke(sortValue: String): List<MovieDomain> {
        val returnedList = mutableListOf<MovieDomain>()
        return getMovies(returnedList, sortValue, Constants.PAGE_INITIAL_VALUE)
    }

    private suspend fun getMovies(
        returnedList: MutableList<MovieDomain>,
        sortValue: String,
        page: Int
    ): List<MovieDomain>{

        val fetchedListMovies = moviesRepository.getListMovies(sortValue, page)

        fetchedListMovies.map {
            returnedList.add(it)
        }

        return if(page == 5){
            returnedList
        } else {
            val newPage = page + 1
            getMovies(returnedList, sortValue, newPage)
        }
    }

}