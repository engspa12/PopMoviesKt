package com.example.dbm.popularmovieskt.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbm.popularmovieskt.di.DispatchersModule
import com.example.dbm.popularmovieskt.domain.service.IMoviesService
import com.example.dbm.popularmovieskt.presentation.state.FavoritesState
import com.example.dbm.popularmovieskt.presentation.state.MainState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val moviesService: IMoviesService,
    @DispatchersModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _uiState = MutableStateFlow<FavoritesState>(FavoritesState.Loading("Loading Movies..."))
    val uiState: StateFlow<FavoritesState> = _uiState

    fun getFavoriteMovies(){
        viewModelScope.launch(mainDispatcher) {
            val listMovies = moviesService.getFavoriteMovies()
            println("The quantity of movies received is: ${listMovies.size}")
        }
    }

    fun addFavoriteMovie(movieId: Int){
        viewModelScope.launch(mainDispatcher) {
            moviesService.addFavoriteMovie(movieId)
        }
    }

    fun removeFavoriteMovie(movieId: Int) {
        viewModelScope.launch(mainDispatcher) {
            moviesService.removeFavoriteMovie(movieId)
        }
    }
}