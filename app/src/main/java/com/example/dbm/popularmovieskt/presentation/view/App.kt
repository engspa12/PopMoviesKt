package com.example.dbm.popularmovieskt.presentation.view

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.dbm.popularmovieskt.global.Constants
import com.example.dbm.popularmovieskt.presentation.view.components.shared.TopBar

@Composable
fun App(
    context: Context
) {

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
                showMenu = false
                sortValue = newSortValue
            }
        }
    ) { paddingValues ->
        Navigation(
            context = context,
            gridLazyState = gridLazyState,
            navController = navController,
            sortValue = sortValue,
            modifier = Modifier.padding(paddingValues)
        )
    }
}