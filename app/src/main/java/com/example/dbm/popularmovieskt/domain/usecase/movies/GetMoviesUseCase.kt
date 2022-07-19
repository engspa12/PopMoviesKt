package com.example.dbm.popularmovieskt.domain.usecase.movies

import com.example.dbm.popularmovieskt.domain.model.MovieDomain
import com.example.dbm.popularmovieskt.domain.repository.IMoviesRepository
import com.example.dbm.popularmovieskt.domain.util.toView
import com.example.dbm.popularmovieskt.global.Constants
import com.example.dbm.popularmovieskt.presentation.model.MovieView
import javax.inject.Inject

interface IGetMoviesUseCase {
    suspend operator fun invoke(sortValue: String): List<MovieView>
}

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository
) : IGetMoviesUseCase {

    override suspend fun invoke(sortValue: String): List<MovieView> {
        val returnedList = mutableListOf<MovieView>()
        return getMovies(returnedList, sortValue, Constants.PAGE_INITIAL_VALUE)
    }

    private suspend fun getMovies(
        returnedList: MutableList<MovieView>,
        sortValue: String,
        page: Int
    ): List<MovieView>{

        val fetchedListMovies = moviesRepository.getListMovies(sortValue, page)

        fetchedListMovies.map {
            returnedList.add(it.toView())
        }

        return if(page == 5){
            returnedList
        } else {
            val newPage = page + 1
            getMovies(returnedList, sortValue, newPage)
        }
    }

}