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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.utils.Player
import com.iago.orlog.utils.gods

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListGods(player: MutableState<Player>, viewModel: ViewModelOrlog, listState: LazyStaggeredGridState) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val spacedBy = with(LocalDensity.current) {
        screenWidth * 0.03f
    }

    LazyVerticalStaggeredGrid(
        state = listState,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(spacedBy),
        verticalArrangement = Arrangement.spacedBy(spacedBy),
        modifier = Modifier
            .fillMaxSize()
            .padding(spacedBy)
    ) {
        (gods.indices).forEach { index ->
            item {
                GodCard(god = gods[index], player, viewModel)
            }
        }
    }
}