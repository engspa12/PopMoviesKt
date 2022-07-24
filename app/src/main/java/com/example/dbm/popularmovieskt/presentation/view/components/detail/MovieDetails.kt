package com.example.dbm.popularmovieskt.presentation.view.components.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.dbm.popularmovieskt.presentation.model.MovieDetailsView
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieDetails(
    detailsMovie: MovieDetailsView,
    lazyListState: LazyListState,
    modifier: Modifier = Modifier,
    onFavoriteButtonClicked: (Int) -> Unit,
    onTrailerItemClicked: (String) -> Unit
) {
    LazyColumn(
        state = lazyListState,
        modifier = modifier
    ) {
        item {
            Text(
                text = detailsMovie.movieName,
                fontSize = 36.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .background(Color(0xFF009688))
                    .padding(vertical = 44.dp, horizontal = 28.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 20.dp, start = 24.dp, end = 24.dp)
            ) {
                GlideImage(
                    imageModel = detailsMovie.moviePosterPath,
                    modifier = Modifier
                        .requiredWidth(150.dp)
                        .requiredHeight(300.dp)
                        .weight(1f),
                    requestOptions = {
                        RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .centerCrop()
                    },
                    shimmerParams = ShimmerParams(
                        baseColor = MaterialTheme.colors.background,
                        highlightColor = Color.Green,
                        durationMillis = 350,
                        dropOff = 0.65f,
                        tilt = 20f
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                )
                Column(
                    modifier = Modifier
                        .requiredHeight(300.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W300,
                        text = detailsMovie.movieReleaseDate
                    )
                    Text(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.W300,
                        text = "${detailsMovie.movieRating}/10"
                    )
                    Button(
                        onClick = {
                            onFavoriteButtonClicked(detailsMovie.movieId)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF009688))
                    ) {
                        Text(
                            text = if (detailsMovie.isFavorite) "Remove from favorites" else "Add to favorites",
                            fontSize = 14.sp
                        )
                    }
                }
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 28.dp),
                text = detailsMovie.movieSynopsis,
                fontSize = 18.sp
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .padding(horizontal = 28.dp)
                    .background(Color(0xFF38B33D))
            )
        }
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 28.dp),
                text = "Trailers",
                fontWeight = FontWeight.W600,
                fontSize = 32.sp
            )
        }
        if (detailsMovie.trailers != null && detailsMovie.trailers.isNotEmpty()) {
            itemsIndexed(detailsMovie.trailers) { index, trailer ->
                Trailer(
                    trailerView = trailer,
                    onTrailerClicked = {
                        onTrailerItemClicked(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.CenterVertically)
                        .padding(vertical = 8.dp, horizontal = 28.dp)
                )
            }
        } else {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.CenterVertically)
                        .padding(vertical = 8.dp, horizontal = 28.dp),
                    fontWeight = FontWeight.W600,
                    text = "There are no trailers available",
                    fontSize = 16.sp
                )
            }
        }
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 28.dp),
                fontWeight = FontWeight.W600,
                text = "Reviews",
                fontSize = 32.sp
            )
        }
        if (detailsMovie.reviews != null && detailsMovie.reviews.isNotEmpty()) {
            itemsIndexed(detailsMovie.reviews) { index, review ->
                Review(
                    reviewView = review,
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 28.dp)
                )
            }
        } else {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.CenterVertically)
                        .padding(vertical = 8.dp, horizontal = 28.dp),
                    fontWeight = FontWeight.W600,
                    text = "There are no reviews available",
                    fontSize = 16.sp
                )
            }
        }
    }
}