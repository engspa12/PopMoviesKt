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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dbm.popularmovieskt.R
import com.example.dbm.popularmovieskt.global.Constants
import com.example.dbm.popularmovieskt.presentation.mapToStringResource
import com.example.dbm.popularmovieskt.presentation.view.components.detail.MovieDetails
import com.example.dbm.popularmovieskt.presentation.view.components.shared.ErrorIndicator
import com.example.dbm.popularmovieskt.presentation.view.components.shared.ProgressBar
import com.example.dbm.popularmovieskt.presentation.viewmodel.DetailsViewModel

@Composable
fun MovieDetailsScreen(
    context: Context,
    movieId: Int,
    viewModel: DetailsViewModel,
    onMovieTitleChange: (String) -> Unit
){

    val lazyListState = rememberLazyListState()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.getMovieDetails(movieId)
    }

    if(uiState.isLoading){
        onMovieTitleChange(stringResource(id = R.string.loading_movie_details))
        ProgressBar(
            message = stringResource(id = R.string.loading_movie_details),
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(Alignment.CenterVertically)
        )
    } else {
        if(uiState.errorPresent){
            onMovieTitleChange(stringResource(id = R.string.error_retrieving_data))
            ErrorIndicator(
                errorMessage = stringResource(id = uiState.errorType.mapToStringResource()),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(horizontal = 20.dp)
            )
        } else {
            uiState.movieDetailsView?.let { movieDetails ->
                onMovieTitleChange(movieDetails.movieName)
                MovieDetails(
                    movieDetails,
                    lazyListState,
                    onFavoriteButtonClicked = { movieId ->
                        viewModel.handleFavoriteEdition(movieId)
                    },
                    onTrailerItemClicked = { trailerKey ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("${Constants.YOUTUBE_URL}${trailerKey}"))
                        if (intent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(intent)
                        }
                    }
                )
            }

        }
    }
}