package com.example.dbm.popularmovieskt.presentation.view.components.shared

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.dbm.popularmovieskt.R
import com.example.dbm.popularmovieskt.global.Constants

@Composable
fun TopBar(
    showMenu: Boolean,
    onDismissMenu: () -> Unit,
    onMenuIconClick: () -> Unit,
    onMenuItemClick: (String) -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {

        },
        actions = {
            // RowScope here, so these icons will be placed horizontally
            IconButton(onClick = {
                onMenuIconClick()
            }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "Localized description")
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
    )
}