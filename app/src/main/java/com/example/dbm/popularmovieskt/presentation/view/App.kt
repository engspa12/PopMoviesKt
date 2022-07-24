package com.example.dbm.popularmovieskt.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.dbm.popularmovieskt.global.Constants
import com.example.dbm.popularmovieskt.presentation.view.components.shared.TopBar

@Composable
fun App() {

    val gridLazyState = rememberLazyGridState()
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    var showMenu by remember { mutableStateOf(false) }
    var sortValue by rememberSaveable { mutableStateOf(Constants.SORT_BY_POPULAR) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                showMenu = showMenu,
                onDismissMenu = {
                    showMenu = false
                },
                onMenuIconClick = {
                    showMenu = !showMenu
                }
            ) { newSortValue ->
                //println("Recomposition TopBar new sort value in AppBar is: $newSortValue")
                showMenu = false
                sortValue = newSortValue
            }
        }
    ) { paddingValues ->
        //println("Recomposition App sort value in Scaffold is: $sortValue")
        /*Dummy(
            sortValue = sortValue,
            modifier = Modifier.padding(paddingValues)/*,
            changeSortValue = {
                sortValue = it
            }*/
        )*/
        Navigation(
            gridLazyState = gridLazyState,
            navController = navController,
            sortValue = sortValue,
            modifier = Modifier.padding(paddingValues)
        )
        /*NavHost(navController = navController, startDestination = "movies"){
            navigation(startDestination = Screen.MainScreen.route, route = "movies") {
                composable(route = Screen.MainScreen.route) {
                    //println("Recomposition Navigation sort value in route is: $sortValue")
                    val mainViewModel = hiltViewModel<MainViewModel>()
                    MoviesGridScreen(
                        lazyState = lazyState,
                        navController = navController,
                        viewModel = mainViewModel,
                        sortValue = sortValue,
                        modifier = Modifier.padding(paddingValues)
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
                    val movieDetailsViewModel = hiltViewModel<DetailsViewModel>()
                    MovieDetailsScreen(
                        context = LocalContext.current,
                        movieId = backStackEntry.arguments?.getInt("movieId") ?: -1,
                        viewModel = movieDetailsViewModel,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }*/
    }
}

@Composable
fun Dummy(
    sortValue: String,
    modifier: Modifier = Modifier/*,
    changeSortValue: (String) -> Unit*/
){
    Text(
        text = sortValue,
        textAlign = TextAlign.Center,
        fontSize = 32.sp,
        modifier = modifier
            .fillMaxSize()
            .wrapContentHeight(align = Alignment.CenterVertically)
            .clickable {
                //changeSortValue("1234")
            }
    )
}