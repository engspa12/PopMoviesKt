package com.example.dbm.popularmovieskt.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbm.popularmovieskt.R
import com.example.dbm.popularmovieskt.di.DispatchersModule
import com.example.dbm.popularmovieskt.domain.service.IMoviesService
import com.example.dbm.popularmovieskt.presentation.state.MainState
import com.example.dbm.popularmovieskt.util.MessageWrapper
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

    private val _uiState = MutableStateFlow(MainState(isLoading = true, messageWrapper = MessageWrapper(messageResource = R.string.loading_movies)))
    val uiState: StateFlow<MainState> = _uiState

    fun getMovies(sortValue: String){

        showProgressBar()

        viewModelScope.launch(mainDispatcher) {
            when(val result = moviesService.getListMovies(sortValue)) {
                is ResultWrapper.Success -> {
                    _uiState.update {
                        it.copy(listMoviesGrid = result.value, errorPresent = false, isLoading = false, messageWrapper = null)
                    }
                }
                is ResultWrapper.Failure -> {
                    _uiState.update {
                        it.copy(errorPresent = true, isLoading = false, messageWrapper = result.errorMessage)
                    }
                }
            }
        }
    }

    private fun showProgressBar() {
        _uiState.update {
            it.copy(isLoading = true, messageWrapper = MessageWrapper(messageResource = R.string.loading_movies))
        }
    }

}