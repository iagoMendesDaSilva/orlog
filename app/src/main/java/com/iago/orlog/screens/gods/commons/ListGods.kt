package com.iago.orlog.screens.gods.commons

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.utils.Player
import com.iago.orlog.utils.gods

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListGods(player: MutableState<Player>, viewModel: ViewModelOrlog, listState: LazyStaggeredGridState) {
    LazyVerticalStaggeredGrid(
        state = listState,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        (gods.indices).forEach { index ->
            item {
                GodCard(god = gods[index], player, viewModel)
            }
        }
    }
}