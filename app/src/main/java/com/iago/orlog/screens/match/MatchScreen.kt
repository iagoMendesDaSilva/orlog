package com.iago.orlog.screens.match

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.screens.coin.commons.Coin
import com.iago.orlog.screens.match.commons.*
import com.iago.orlog.utils.*

@Composable
fun MatchScreen(navController: NavHostController, viewModel: ViewModelOrlog) {

    val rotate =
        viewModel.mode.value === MODES.ONE_PLAYER && viewModel.iaTurn.value == viewModel.player1.value.coinFace

    val dicesTablePlayer1 = remember { mutableStateOf(getRandomDiceSides()) }
    val dicesTablePlayer2 = remember { mutableStateOf(getRandomDiceSides()) }

    val dicesSelectedPlayer1 = remember { mutableStateOf<List<DiceSide>>(emptyList()) }
    val dicesSelectedPlayer2 = remember { mutableStateOf<List<DiceSide>>(emptyList()) }


    var currentRotation = remember { mutableStateOf(0f) }
    val rotation = remember { Animatable(currentRotation.value) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Coin(rotation, viewModel.turn.value, viewModel, 100.dp) {}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
            .rotate(if (rotate) 180f else 0f),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PlayerTable(
            Modifier
                .fillMaxHeight()
                .weight(1f)
                .rotate(180f), viewModel, viewModel.player2, dicesSelectedPlayer2, dicesTablePlayer2
        )
        MatchDivision(viewModel, rotate, rotation, currentRotation)
        PlayerTable(
            Modifier
                .fillMaxHeight()
                .weight(1f), viewModel, viewModel.player1, dicesSelectedPlayer1, dicesTablePlayer1
        )
    }
}

@Composable
fun PlayerTable(
    modifier: Modifier,
    viewModel: ViewModelOrlog,
    player: MutableState<Player>,
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
    dicesTablePlayer: MutableState<MutableList<DiceSide>>
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        HeaderStatus(player, dicesTablePlayer, viewModel)
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            RowDices(dicesSelectedPlayer, dicesTablePlayer)
            RowSelectedDices(dicesSelectedPlayer.value)
            FooterStatus(player.value)
        }
    }
}

fun getRandomDiceSides(diceSides: MutableList<DiceSide>? = null): MutableList<DiceSide> {
    var dices = dices.mapIndexed { index, dice ->
        val item = dice.sides[(dice.sides.indices.random())]
        val favor = dice.tokenSides.contains(item.side)
        item.copy(favor = favor, idDice = index)
    }.toMutableList()

    if (diceSides != null) {
        return dices.filterIndexed { index, _ ->
            diceSides.any { it.idDice == index }
        }.toMutableList()
    }

    return dices
}