package com.example.dbm.popularmovieskt.presentation.view.components.main

import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieGridItem(
    urlImage: String,
    onItemClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    GlideImage(
        imageModel = urlImage,
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
        modifier = modifier
            .clickable {
                onItemClicked()
            }
    )
}