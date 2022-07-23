package com.example.dbm.popularmovieskt.presentation.view.components.main

import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dbm.popularmovieskt.presentation.model.MovieGridView

@Composable
fun MoviesGrid(
    lazyState: LazyGridState,
    list: List<MovieGridView>?,
    onItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = lazyState,
        modifier = modifier
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