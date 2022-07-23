package com.example.dbm.popularmovieskt.presentation.view.components.detail
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dbm.popularmovieskt.R
import com.example.dbm.popularmovieskt.presentation.model.TrailerView

@Composable
fun Trailer(
    trailerView: TrailerView,
    onTrailerClicked: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "",
                modifier = Modifier.clickable {
                    onTrailerClicked(trailerView.key)
                }
            )
            Text(
                text = trailerView.name,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_share),
                contentDescription = "",
                modifier = Modifier.size(36.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .height(2.dp)
                .background(Color(0xFF38B33D))
        )
    }

}