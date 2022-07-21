package com.example.dbm.popularmovieskt.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbm.popularmovieskt.di.DispatchersModule
import com.example.dbm.popularmovieskt.domain.service.IMoviesService
import com.example.dbm.popularmovieskt.domain.usecase.movies.IGetMoviesUseCase
import com.example.dbm.popularmovieskt.global.Constants
import com.example.dbm.popularmovieskt.presentation.state.MainState
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

    private val _uiState = MutableStateFlow<MainState>(MainState.Loading("Loading Movies..."))
    val uiState: StateFlow<MainState> = _uiState

    fun getMovies(sortValue: String){
        viewModelScope.launch(mainDispatcher) {
            val listMovies = moviesService.getListMovies(sortValue)
            _uiState.value = MainState.Success(value = listMovies)
        }
    }

}