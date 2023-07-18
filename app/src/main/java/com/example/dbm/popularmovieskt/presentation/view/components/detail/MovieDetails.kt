package com.example.dbm.popularmovieskt.presentation.view.components.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.dbm.popularmovieskt.R
import com.example.dbm.popularmovieskt.presentation.model.MovieDetailsView
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieDetails(
    detailsMovie: MovieDetailsView,
    lazyListState: LazyListState,
    onFavoriteButtonClicked: (Int) -> Unit,
    onTrailerItemClicked: (String) -> Unit,
    modifier: Modifier = Modifier
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
                        text = stringResource(id = R.string.movie_rating, detailsMovie.movieRating)
                    )
                    Button(
                        onClick = {
                            onFavoriteButtonClicked(detailsMovie.movieId)
                        },
                        colors =
                        if (detailsMovie.isFavorite)
                            ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFB40F02)
                            )
                        else
                            ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFF026D63)
                            )
                    ) {
                        Text(
                            text =
                            if (detailsMovie.isFavorite)
                                stringResource(id = R.string.remove_from_favorites)
                            else
                                stringResource(id = R.string.add_to_favorites),
                            modifier = Modifier
                                .width(100.dp)
                                .height(30.dp)
                                .wrapContentHeight(align = Alignment.CenterVertically),
                            textAlign = TextAlign.Center,
                            color = Color(0xFFFFFFFF),
                            fontSize = 12.sp
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
                text = stringResource(id = R.string.trailers_title),
                fontWeight = FontWeight.W600,
                fontSize = 32.sp
            )
        }
        if (detailsMovie.trailers != null) {
            if(detailsMovie.trailers.isNotEmpty()) {
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
                        text = stringResource(id = R.string.no_trailers_available_message),
                        fontSize = 16.sp
                    )
                }
            }
        } else {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.CenterVertically)
                        .padding(vertical = 8.dp, horizontal = 28.dp),
                    fontWeight = FontWeight.W600,
                    text = stringResource(id = R.string.no_internet_connection),
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
                text = stringResource(id = R.string.reviews_title),
                fontSize = 32.sp
            )
        }
        if (detailsMovie.reviews != null) {
            if(detailsMovie.reviews.isNotEmpty()){
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
                        text = stringResource(id = R.string.no_reviews_available_message),
                        fontSize = 16.sp
                    )
                }
            }
        } else {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.CenterVertically)
                        .padding(vertical = 8.dp, horizontal = 28.dp),
                    fontWeight = FontWeight.W600,
                    text = stringResource(id = R.string.no_internet_connection),
                    fontSize = 16.sp
                )
            }
        }
    }
}