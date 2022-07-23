package com.example.dbm.popularmovieskt.presentation.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dbm.popularmovieskt.global.Constants
import com.example.dbm.popularmovieskt.presentation.view.components.shared.AppBar
import com.example.dbm.popularmovieskt.presentation.view.screens.MovieDetailsScreen
import com.example.dbm.popularmovieskt.presentation.viewmodel.DetailsViewModel
import com.example.dbm.popularmovieskt.presentation.viewmodel.MainViewModel

@Composable
fun App() {
    val viewModel: MainViewModel = viewModel()
    val detailsViewModel: DetailsViewModel = viewModel()
    val scaffoldState = rememberScaffoldState()
    var showMenu by remember { mutableStateOf(false) }
    var sortValue by remember { mutableStateOf(Constants.SORT_BY_POPULAR) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                showMenu = showMenu,
                onDismissMenu = {
                    showMenu = false
                },
                onMenuIconClick = {
                    showMenu = !showMenu
                }
            ) { newSortValue ->
                println("New sort value is $newSortValue")
                showMenu = false
                sortValue = newSortValue
            }
        }
    ) { paddingValues ->
        /*MoviesGridScreen(
            viewModel = viewModel,
            sortValue = sortValue,
            modifier = Modifier.padding(paddingValues)
        )*/
        MovieDetailsScreen(
            context = LocalContext.current,
            viewModel = detailsViewModel,
            movieId = 507086,
            modifier = Modifier.padding(paddingValues)
        )
    }

}