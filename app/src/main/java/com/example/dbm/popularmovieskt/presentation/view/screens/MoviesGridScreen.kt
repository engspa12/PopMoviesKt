package com.example.dbm.popularmovieskt.presentation.view.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dbm.popularmovieskt.presentation.state.MainState
import com.example.dbm.popularmovieskt.presentation.navigation.Screen
import com.example.dbm.popularmovieskt.presentation.view.components.main.MoviesGrid
import com.example.dbm.popularmovieskt.presentation.view.components.shared.ErrorIndicator
import com.example.dbm.popularmovieskt.presentation.view.components.shared.ProgressBar
import com.example.dbm.popularmovieskt.presentation.viewmodel.MainViewModel

@Composable
fun MoviesGridScreen(
    gridLazyState: LazyGridState,
    navController: NavController,
    viewModel: MainViewModel,
    sortValue: String
){

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = sortValue) {
        viewModel.getMovies(sortValue)
    }

    when(uiState) {
        is MainState.Success -> {
            MoviesGrid(
                gridLazyState = gridLazyState,
                list = uiState.value,
                onItemClicked = { movieId ->
                    navController.navigate(Screen.DetailScreen.withArgs(movieId))
                }
            )
        }
        is MainState.Error -> {
            ErrorIndicator(
                errorMessage = uiState.errorMessage.asString(),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(horizontal = 20.dp)
            )
        }
        is MainState.Loading -> {
            ProgressBar(
                message = uiState.loadingMessage.asString(),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
    }

}