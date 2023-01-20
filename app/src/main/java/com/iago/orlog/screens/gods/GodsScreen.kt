package com.iago.orlog.screens.gods

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.navigation.Screens
import com.iago.orlog.screens.gods.commons.Header
import com.iago.orlog.screens.gods.commons.ListGods
import com.iago.orlog.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GodsScreen(navController: NavHostController, viewModel: ViewModelOrlog) {

    var player = viewModel.getCurrentPlayer()

    val listState = rememberLazyStaggeredGridState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = viewModel.turn.value) {
        if (viewModel.mode.value === MODES.ONE_PLAYER && viewModel.turn.value === viewModel.iaTurn.value)
            selectGods(viewModel, navController, coroutineScope, listState)
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
    if (viewModel.turn.value === viewModel.player1.value.coinFace)
        viewModel.changeTurn()
    else
        navController.navigate(Screens.MatchScreen.name)
}

fun getGods(): MutableList<GodIndexed> {
    val randomInts = generateSequence { Random.nextInt(gods.indices) }
        .distinct()
        .take(3)
        .sorted()
        .toList()

    val index0 = randomInts.elementAt(0)
    val index1 = randomInts.elementAt(1)
    val index2 = randomInts.elementAt(2)

    return mutableListOf<GodIndexed>(
        GodIndexed(index0, gods[index0]),
        GodIndexed(index1, gods[index1]),
        GodIndexed(index2, gods[index2]),
    )
}

@OptIn(ExperimentalFoundationApi::class)
fun scrollList(index: Int, coroutineScope: CoroutineScope, listState: LazyStaggeredGridState) {
    coroutineScope.launch {
        listState.animateScrollToItem(index)
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun selectGods(
    viewModel: ViewModelOrlog,
    navController: NavHostController,
    coroutineScope: CoroutineScope,
    listState: LazyStaggeredGridState,
) {

    val listGods = getGods()
    val listIterations = mutableListOf<() -> Unit>()

    listGods.forEach { godIndexed ->
        listIterations.add {
            scrollList(godIndexed.index, coroutineScope, listState)
        }
        listIterations.add {
            viewModel.updateGodsList(viewModel.getCurrentPlayer().value.gods, true, godIndexed.god, viewModel)
        }
    }
    listIterations.add {
        scrollList(0, coroutineScope, listState)
    }

    listIterations.add {
        pressConfirmButton(navController, viewModel)
    }

    viewModel.makeDecisionDelayed(600, listIterations, coroutineScope)
}

