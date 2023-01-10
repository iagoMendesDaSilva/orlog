package com.iago.orlog.screens.gods

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.iago.orlog.utils.God
import com.iago.orlog.utils.gods

@Composable
fun GodsScreens(navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        ListGods()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListGods() {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        (0..gods.size).forEach { index ->
            item {
                GodCard(god = gods[index])
            }
        }
    }
}

@Composable
fun GodCard(god: God) {
    Box(
        modifier = Modifier
            .fillMaxWidth(.5f)
            .background(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colors.onBackground
            )
            .border(
                width = 2.dp,
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colors.secondary,
            )
    ) {
        Image(
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth(.7f)
                .padding(10.dp),
            painter = rememberAsyncImagePainter(god.img),
            contentDescription = stringResource(id = god.name),
        )
    }
}
