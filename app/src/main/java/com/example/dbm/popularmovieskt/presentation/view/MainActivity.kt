package com.example.dbm.popularmovieskt.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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

                    Greeting("PopMoviesKt")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        fontSize = 24.sp,
        letterSpacing = 8.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF041991))
            .wrapContentHeight(align = Alignment.CenterVertically)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PopMoviesKtTheme {
        Greeting("Android")
    }
}