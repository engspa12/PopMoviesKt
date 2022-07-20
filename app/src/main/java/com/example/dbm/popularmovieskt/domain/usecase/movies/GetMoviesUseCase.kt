package com.example.dbm.popularmovieskt.domain.usecase.movies

import com.example.dbm.popularmovieskt.domain.model.MovieDomain
import com.example.dbm.popularmovieskt.domain.repository.IMoviesRepository
import com.example.dbm.popularmovieskt.domain.util.toDetailsView
import com.example.dbm.popularmovieskt.domain.util.toView
import com.example.dbm.popularmovieskt.global.Constants
import com.example.dbm.popularmovieskt.presentation.model.MovieDetailsView
import javax.inject.Inject

interface IGetMoviesUseCase {
    suspend operator fun invoke(sortValue: String): List<MovieDetailsView>
}

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository
) : IGetMoviesUseCase {

    private var listMovies = mutableListOf<MovieDomain>()

    override suspend fun invoke(sortValue: String): List<MovieDetailsView> {
        val returnedList = mutableListOf<MovieDetailsView>()
        return getMovies(returnedList, sortValue, Constants.PAGE_INITIAL_VALUE)
    }

    private suspend fun getMovies(
        returnedList: MutableList<MovieDetailsView>,
        sortValue: String,
        page: Int
    ): List<MovieDetailsView>{

        val fetchedListMovies = moviesRepository.getListMovies(sortValue, page)

        fetchedListMovies.map {
            listMovies.add(it)
            returnedList.add(it.toDetailsView())
        }

        return if(page == 5){
            returnedList
        } else {
            val newPage = page + 1
            getMovies(returnedList, sortValue, newPage)
        }
    }

}