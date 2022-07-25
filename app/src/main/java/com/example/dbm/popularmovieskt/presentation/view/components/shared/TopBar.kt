package com.example.dbm.popularmovieskt.presentation.view.components.shared

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.dbm.popularmovieskt.global.Constants

@Composable
fun TopBar(
    showMenu: Boolean,
    titleTopBar: String,
    navigationType: Constants.NavType,
    navController: NavHostController,
    onDismissMenu: () -> Unit,
    onMenuIconClick: () -> Unit,
    onMenuItemClick: (String) -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = titleTopBar,
                color = MaterialTheme.colors.onPrimary
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            when(navigationType) {
                Constants.NavType.NAV_MAIN -> {
                    IconButton(onClick = {

                    }) {

                    }
                }
                Constants.NavType.NAV_DETAIL -> {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Go back to main",
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
        },
        actions = {
            // RowScope here, so these icons will be placed horizontally
            when(navigationType) {
                Constants.NavType.NAV_MAIN -> {
                    IconButton(onClick = {
                        onMenuIconClick()
                    }) {
                        Icon(
                            Icons.Filled.MoreVert,
                            contentDescription = "More options",
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu ,
                        onDismissRequest = { onDismissMenu() })
                    {
                        DropdownMenuItem(
                            onClick = {
                                onMenuItemClick(Constants.SORT_BY_POPULAR)
                            }
                        ) {
                            Text(text = "Sort By Popular")
                        }
                        DropdownMenuItem(
                            onClick = {
                                onMenuItemClick(Constants.SORT_BY_HIGHEST_RATED)
                            }
                        ) {
                            Text(text = "Sort By Highest Rated")
                        }
                        DropdownMenuItem(
                            onClick = {
                                onMenuItemClick(Constants.SORT_BY_FAVORITE_MOVIES)
                            }
                        ) {
                            Text(text = "Sort By Favorites")
                        }
                    }
                }
                Constants.NavType.NAV_DETAIL -> {
                    IconButton(onClick = {

                    }) {

                    }
                }
            }

        }
    )
}