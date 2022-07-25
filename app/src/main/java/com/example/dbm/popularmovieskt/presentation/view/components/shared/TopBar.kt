package com.example.dbm.popularmovieskt.presentation.view.components.shared

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.dbm.popularmovieskt.R
import com.example.dbm.popularmovieskt.global.Constants

@Composable
fun TopBar(
    showMenu: Boolean,
    navigationChange: Constants.NavType,
    navController: NavHostController,
    onDismissMenu: () -> Unit,
    onMenuIconClick: () -> Unit,
    onMenuItemClick: (String) -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colors.onPrimary
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            when(navigationChange) {
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
            when(navigationChange) {
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
                            Text(text = "Sort By Favorite Movies")
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