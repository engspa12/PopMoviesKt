package com.example.dbm.popularmovieskt.presentation.view.components.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dbm.popularmovieskt.R
import com.example.dbm.popularmovieskt.presentation.model.ReviewView

@Composable
fun Review(
    reviewView: ReviewView,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.written_by, reviewView.author),
            fontWeight = FontWeight.W600,
            fontStyle = FontStyle.Italic,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = reviewView.content,
            fontWeight = FontWeight.W200,
            fontSize = 16.sp
        )
    }

}