package com.example.dbm.popularmovieskt.presentation.navigation

import android.content.Context
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.dbm.popularmovieskt.R
import com.example.dbm.popularmovieskt.global.Constants
import com.example.dbm.popularmovieskt.presentation.view.screens.MovieDetailsScreen
import com.example.dbm.popularmovieskt.presentation.view.screens.MoviesGridScreen
import com.example.dbm.popularmovieskt.presentation.viewmodel.DetailsViewModel
import com.example.dbm.popularmovieskt.presentation.viewmodel.MainViewModel

@Composable
fun Navigation(
    context: Context,
    gridLazyState: LazyGridState,
    navController: NavHostController,
    sortValue: String,
    onNavigationChange: (Constants.NavType) -> Unit,
    onTitleChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    NavHost(navController = navController, startDestination = "movies", modifier = modifier){
        navigation(startDestination = Screen.MainScreen.route + "/sortValue", route = "movies") {
            composable(
                route = Screen.MainScreen.route + "/sortValue",
                arguments = listOf(
                    navArgument("sortValue") {
                        type = NavType.StringType
                        defaultValue = sortValue
                        nullable = false
                    }
                )
                ) {
                onNavigationChange(Constants.NavType.NAV_MAIN)
                val mainViewModel = hiltViewModel<MainViewModel>()

                when(sortValue) {
                    Constants.SORT_BY_POPULAR -> {
                        onTitleChange(context.getString(R.string.popular_movies_title))
                    }
                    Constants.SORT_BY_HIGHEST_RATED -> {
                        onTitleChange(context.getString(R.string.highest_rated_movies_title))
                    }
                    Constants.SORT_BY_FAVORITE_MOVIES -> {
                        onTitleChange(context.getString(R.string.favorite_movies_title))
                    }
                }

                MoviesGridScreen(
                    gridLazyState = gridLazyState,
                    navigateToDetailsScreen = { movieId ->
                        navController.navigate(Screen.DetailScreen.withArgs(movieId))
                    },
                    viewModel = mainViewModel,
                    sortValue = sortValue
                )
            }
            composable(
                route = Screen.DetailScreen.route + "/{movieId}",
                arguments = listOf(
                    navArgument("movieId") {
                        type = NavType.IntType
                        defaultValue = -1
                        nullable = false
                    }
                )
            ) { backStackEntry ->
                onNavigationChange(Constants.NavType.NAV_DETAILS)
                val movieDetailsViewModel = hiltViewModel<DetailsViewModel>()
                MovieDetailsScreen(
                    context = context,
                    onMovieTitleChange = { movieTitle ->
                        onTitleChange(movieTitle)
                    },
                    movieId = backStackEntry.arguments?.getInt("movieId") ?: -1,
                    viewModel = movieDetailsViewModel
                )
            }
        }
    }
}