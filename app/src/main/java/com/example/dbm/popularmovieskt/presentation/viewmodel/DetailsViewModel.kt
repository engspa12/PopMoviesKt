package com.example.dbm.popularmovieskt.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbm.popularmovieskt.R
import com.example.dbm.popularmovieskt.di.DispatchersModule
import com.example.dbm.popularmovieskt.domain.service.IMoviesService
import com.example.dbm.popularmovieskt.presentation.state.DetailsState
import com.example.dbm.popularmovieskt.util.MessageWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val moviesService: IMoviesService,
    @DispatchersModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _uiState = MutableStateFlow(DetailsState(isLoading = true, messageWrapper = MessageWrapper(messageResource = R.string.loading_movie_details)))
    val uiState: StateFlow<DetailsState> = _uiState

    fun getMovieDetails(movieId: Int){
        viewModelScope.launch(mainDispatcher) {
            val movieDetails = moviesService.getMovieDetails(movieId)
            _uiState.update {
                it.copy(movieDetailsView = movieDetails, isLoading = false, messageWrapper = null)
            }
        }
    }

    fun handleFavoriteEdition(movieId: Int) {
        viewModelScope.launch(mainDispatcher) {
            moviesService.handleFavoriteMovieEdition(movieId)
            getMovieDetails(movieId)
        }
    }
}