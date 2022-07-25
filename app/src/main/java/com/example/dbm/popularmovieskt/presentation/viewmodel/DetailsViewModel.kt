package com.example.dbm.popularmovieskt.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbm.popularmovieskt.di.DispatchersModule
import com.example.dbm.popularmovieskt.domain.service.IMoviesService
import com.example.dbm.popularmovieskt.presentation.state.DetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val moviesService: IMoviesService,
    @DispatchersModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _uiState = MutableStateFlow<DetailsState>(DetailsState.Loading("Loading Details..."))
    val uiState: StateFlow<DetailsState> = _uiState

    fun getMovieDetails(movieId: Int){
        viewModelScope.launch(mainDispatcher) {
            val movieDetails = moviesService.getMovieDetails(movieId)
            _uiState.value = DetailsState.Success(value = movieDetails)
        }
    }

    fun handleFavoriteEdition(movieId: Int) {
        viewModelScope.launch(mainDispatcher) {
            moviesService.handleFavoriteEdition(movieId)
            getMovieDetails(movieId)
        }
    }
}