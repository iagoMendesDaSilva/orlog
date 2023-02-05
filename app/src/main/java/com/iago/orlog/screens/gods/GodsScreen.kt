package com.iago.orlog.screens.gods

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.navigation.Screens
import com.iago.orlog.screens.gods.commons.Header
import com.iago.orlog.screens.gods.commons.ListGods
import com.iago.orlog.utils.*
import kotlin.random.Random
import kotlin.random.nextInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GodsScreen(navController: NavHostController, viewModel: ViewModelOrlog) {

    val player = viewModel.getCurrentPlayer()

    val listState = rememberLazyStaggeredGridState()

    LaunchedEffect(key1 = viewModel.turn.value) {
        if (viewModel.mode.value === MODES.ONE_PLAYER && viewModel.turn.value === viewModel.iaTurn.value)
            selectGods(viewModel, navController)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(navController, player) {
            pressConfirmButton(navController, viewModel)
        }
        ListGods(player, viewModel, listState)
    }
}

fun pressConfirmButton(navController: NavHostController, viewModel: ViewModelOrlog) {
    if (viewModel.turn.value != viewModel.player1.value.coinFace) {
        viewModel.changeTurn()
        navController.navigate(Screens.MatchScreen.name)
    } else
        viewModel.changeTurn()

}

fun getGods(): MutableList<God> {
    val randomInts = generateSequence { Random.nextInt(gods.indices) }
        .distinct()
        .take(3)
        .sorted()
        .toList()

    return mutableListOf(
        gods[randomInts[0]],
        gods[randomInts[1]],
        gods[randomInts[2]],
    )
}

fun selectGods(
    viewModel: ViewModelOrlog,
    navController: NavHostController,
) {

    val listGods = getGods()

    listGods.forEach {
        viewModel.updateGodsList(
            viewModel.getCurrentPlayer().value.godFavors,
            true,
            it,
            viewModel
        )
    }
    pressConfirmButton(navController, viewModel)
}

