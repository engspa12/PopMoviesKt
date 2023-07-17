package com.example.dbm.popularmovieskt.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbm.popularmovieskt.di.DispatchersModule
import com.example.dbm.popularmovieskt.domain.service.IMoviesService
import com.example.dbm.popularmovieskt.domain.util.MoviesDomainError
import com.example.dbm.popularmovieskt.presentation.util.MoviesViewError
import com.example.dbm.popularmovieskt.presentation.state.MainState
import com.example.dbm.popularmovieskt.util.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val moviesService: IMoviesService,
    @DispatchersModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _uiState = MutableStateFlow(MainState(isLoading = true))
    val uiState: StateFlow<MainState> = _uiState

    fun getMovies(sortValue: String){

        showProgressBar()

        viewModelScope.launch(mainDispatcher) {
            when(val result = moviesService.getListMovies(sortValue)) {
                is ResultWrapper.Success -> {
                    _uiState.update {
                        it.copy(listMoviesGrid = result.value, errorPresent = false, isLoading = false, errorType = MoviesViewError.NONE)
                    }
                }
                is ResultWrapper.Failure -> {
                    _uiState.update {
                        it.copy(errorPresent = true, isLoading = false, errorType = processErrorResponse(result.error))
                    }
                }
            }
        }
    }

    private fun showProgressBar() {
        _uiState.update {
            it.copy(isLoading = true)
        }
    }

    private fun processErrorResponse(errorDomain: MoviesDomainError?): MoviesViewError {
        return when(errorDomain){
            MoviesDomainError.NO_FAVORITE_MOVIES_FOUND -> MoviesViewError.NO_FAVORITE_MOVIES_FOUND
            MoviesDomainError.GENERIC -> MoviesViewError.GENERIC
            MoviesDomainError.UNKNOWN -> MoviesViewError.UNKNOWN
            MoviesDomainError.NO_INTERNET_CONNECTION -> MoviesViewError.NO_INTERNET_CONNECTION
            else -> MoviesViewError.GENERIC
        }
    }

}