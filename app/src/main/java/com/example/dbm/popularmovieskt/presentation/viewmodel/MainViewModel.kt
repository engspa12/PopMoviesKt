package com.example.dbm.popularmovieskt.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbm.popularmovieskt.R
import com.example.dbm.popularmovieskt.di.DispatchersModule
import com.example.dbm.popularmovieskt.domain.service.IMoviesService
import com.example.dbm.popularmovieskt.presentation.state.MainState
import com.example.dbm.popularmovieskt.util.ResultWrapper
import com.example.dbm.popularmovieskt.util.StringWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val moviesService: IMoviesService,
    @DispatchersModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _uiState = MutableStateFlow<MainState>(MainState.Loading(StringWrapper.ResourceString(id = R.string.loading_movies)))
    val uiState: StateFlow<MainState> = _uiState

    fun getMovies(sortValue: String){

        showProgressBar()

        viewModelScope.launch(mainDispatcher) {
            when(val result = moviesService.getListMovies(sortValue)) {
                is ResultWrapper.Success -> {
                    _uiState.value = MainState.Success(value = result.value)
                }
                is ResultWrapper.Failure -> {
                    _uiState.value = MainState.Error(errorMessage = result.errorMessage)
                }
            }
        }
    }

    private fun showProgressBar() {
        _uiState.value = MainState.Loading(StringWrapper.ResourceString(id = R.string.loading_movies))
    }

}