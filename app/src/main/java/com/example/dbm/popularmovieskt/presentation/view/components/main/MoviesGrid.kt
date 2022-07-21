package com.example.dbm.popularmovieskt.presentation.view.components.main

import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dbm.popularmovieskt.presentation.model.MovieGridView

@Composable
fun MoviesGrid(
    lazyState: LazyGridState,
    list: List<MovieGridView>?,
    onItemClicked: (Int) -> Unit
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = lazyState
    ) {
        itemsIndexed(list ?: listOf()) { _ , gridItemView ->
            MovieGridItem(
                urlImage = gridItemView.moviePosterPath,
                modifier = Modifier
                .requiredHeight(height = 350.dp)
            ){
                onItemClicked(gridItemView.movieId)
            }
        }
    }

}