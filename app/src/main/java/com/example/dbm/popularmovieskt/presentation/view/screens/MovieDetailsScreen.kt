package com.example.dbm.popularmovieskt.presentation.view.screens

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dbm.popularmovieskt.presentation.state.DetailsState
import com.example.dbm.popularmovieskt.presentation.view.components.detail.MovieDetails
import com.example.dbm.popularmovieskt.presentation.view.components.shared.ErrorIndicator
import com.example.dbm.popularmovieskt.presentation.view.components.shared.ProgressBar
import com.example.dbm.popularmovieskt.presentation.viewmodel.DetailsViewModel

@Composable
fun MovieDetailsScreen(
    context: Context,
    viewModel: DetailsViewModel,
    movieId: Int,
    modifier: Modifier = Modifier
){

    val lazyListState = rememberLazyListState()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getMovieDetails(movieId)
    }

    when(uiState) {
        is DetailsState.Success -> {
            uiState.value?.let { movieDetails ->
                MovieDetails(
                    movieDetails,
                    lazyListState,
                    modifier = modifier,
                    onFavoriteButtonClicked = { movieId ->
                        println("The movieId of the item clicked is: $movieId") },
                    { trailerKey ->

                })
            }
        }
        is DetailsState.Error -> {
            ErrorIndicator(
                errorMessage = uiState.errorMessage,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(horizontal = 20.dp)
            )
        }
        is DetailsState.Loading -> {
            ProgressBar(
                message = uiState.loadingMessage,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
    }

}