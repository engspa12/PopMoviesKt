package com.example.dbm.popularmovieskt.presentation.view.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dbm.popularmovieskt.R
import com.example.dbm.popularmovieskt.presentation.mapToStringResource
import com.example.dbm.popularmovieskt.presentation.view.components.main.MoviesGrid
import com.example.dbm.popularmovieskt.presentation.view.components.shared.ErrorIndicator
import com.example.dbm.popularmovieskt.presentation.view.components.shared.ProgressBar
import com.example.dbm.popularmovieskt.presentation.viewmodel.MainViewModel

@Composable
fun MoviesGridScreen(
    gridLazyState: LazyGridState,
    navigateToDetailsScreen: (Int) -> Unit,
    viewModel: MainViewModel,
    sortValue: String
){

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = sortValue) {
        viewModel.getMovies(sortValue)
    }

    if(uiState.isLoading) {
        ProgressBar(
            message = stringResource(id = R.string.loading_movies),
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(Alignment.CenterVertically)
        )
    } else {
        if(uiState.errorPresent){
            ErrorIndicator(
                errorMessage = stringResource(id = uiState.errorType.mapToStringResource()),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(horizontal = 20.dp)
            )
        } else {
            MoviesGrid(
                gridLazyState = gridLazyState,
                list = uiState.listMoviesGrid,
                onItemClicked = { movieId ->
                    navigateToDetailsScreen(movieId)
                }
            )
        }
    }
}