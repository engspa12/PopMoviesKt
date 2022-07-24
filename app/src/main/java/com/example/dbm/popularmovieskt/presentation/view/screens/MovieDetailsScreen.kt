package com.example.dbm.popularmovieskt.presentation.view.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dbm.popularmovieskt.global.Constants
import com.example.dbm.popularmovieskt.presentation.state.DetailsState
import com.example.dbm.popularmovieskt.presentation.view.components.detail.MovieDetails
import com.example.dbm.popularmovieskt.presentation.view.components.shared.ErrorIndicator
import com.example.dbm.popularmovieskt.presentation.view.components.shared.ProgressBar
import com.example.dbm.popularmovieskt.presentation.viewmodel.DetailsViewModel

@Composable
fun MovieDetailsScreen(
    context: Context,
    movieId: Int,
    viewModel: DetailsViewModel,
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
                        println("The movieId of the item clicked is: $movieId")
                    },
                    onTrailerItemClicked = { trailerKey ->
                        println("The url of the item clicked is: ${Constants.YOUTUBE_URL}${trailerKey}")
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("${Constants.YOUTUBE_URL}${trailerKey}"))
                        if (intent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(intent)
                        }
                    }
                )
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