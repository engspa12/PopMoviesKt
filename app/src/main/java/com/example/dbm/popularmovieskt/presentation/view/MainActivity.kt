package com.example.dbm.popularmovieskt.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dbm.popularmovieskt.global.Constants
import com.example.dbm.popularmovieskt.presentation.view.screens.MoviesGridScreen
import com.example.dbm.popularmovieskt.presentation.view.theme.PopMoviesKtTheme
import com.example.dbm.popularmovieskt.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PopMoviesKtTheme {
                // A surface container using the 'background' color from the theme
                //val state = rememberScrollState()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                        //.verticalScroll(state = state),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel: MainViewModel = viewModel()

                    MoviesGridScreen(
                        viewModel = viewModel,
                        sortValue = Constants.SORT_BY_POPULAR
                    )
                }
            }
        }
    }
}