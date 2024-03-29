package com.example.dbm.popularmovieskt.presentation.view.components.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProgressBar(
    message: String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .requiredSize(50.dp)
        )
        Text(
            text = message,
            color = MaterialTheme.colors.primaryVariant,
            modifier =
            Modifier.padding(vertical = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}