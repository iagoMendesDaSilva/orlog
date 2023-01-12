package com.iago.orlog.screens.gods

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
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
fun GodsScreen(navController: NavHostController) {

    val viewModel = hiltViewModel<ViewModelOrlog>()
    val player = viewModel.getCurrentPlayer()

    val listState = rememberLazyStaggeredGridState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = viewModel.turn.value) {
        if (viewModel.turn.value == COIN.FACE_DOWN && viewModel.mode.value == MODES.ONE_PLAYER)
            selectGods(viewModel, navController, coroutineScope, listState)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(navController, player, viewModel) {
            startMatch(navController)
        }
        ListGods(player, viewModel, listState)
    }
}

fun startMatch(navController: NavHostController) {
    navController.navigate(Screens.InstructionScreen.name)
}

fun getGods(): MutableList<GodIndexed> {
    val randomInts = generateSequence { Random.nextInt(gods.indices) }
        .distinct()
        .take(3)
        .sorted()
        .toList()

    val index0 =randomInts.elementAt(0)
    val index1 =randomInts.elementAt(1)
    val index2 =randomInts.elementAt(2)

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
    listState: LazyStaggeredGridState
) {

    val listGods = getGods()

    val listIterations = listOf({
        viewModel.updateGodsList(
            viewModel.player2.value.gods,
            true,
            listGods[0].god,
            viewModel
        )
    },
        {
            scrollList(listGods[0].index, coroutineScope, listState)
        },
        {
            viewModel.updateGodsList(
                viewModel.player2.value.gods,
                true,
                listGods[1].god,
                viewModel
            )
        },
        {
            scrollList(listGods[1].index, coroutineScope, listState)
        },
        {
            viewModel.updateGodsList(
                viewModel.player2.value.gods,
                true,
                listGods[2].god,
                viewModel
            )
        },
        {
            scrollList(listGods[2].index, coroutineScope, listState)
        },
        {
            startMatch(navController)
        }
    )

    viewModel.makeDecisionDelayed(600, listIterations, coroutineScope)


}
