package com.iago.orlog.screens.match

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
import com.iago.orlog.screens.match.commons.*
import com.iago.orlog.utils.DiceSide
import com.iago.orlog.utils.Player
import com.iago.orlog.utils.dices

@Composable
fun MatchScreen(navController: NavHostController, viewModel: ViewModelOrlog) {

    val dicesTablePlayer1 = remember { mutableStateOf(getRandomDiceSides()) }
    val dicesTablePlayer2 = remember { mutableStateOf(getRandomDiceSides()) }

    val dicesSelectedPlayer1 = remember { mutableStateOf<List<DiceSide>>(emptyList()) }
    val dicesSelectedPlayer2 = remember { mutableStateOf<List<DiceSide>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PlayerTable(
            Modifier
                .fillMaxHeight()
                .weight(1f)
                .rotate(180f), viewModel, viewModel.player2, dicesSelectedPlayer2, dicesTablePlayer2
        )
        MatchDivision(viewModel)
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
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.End
    ) {
        Reroll(player, viewModel)
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            RowDices(dicesSelectedPlayer, dicesTablePlayer)
            RowSelectedDices(dicesSelectedPlayer.value)
            StatusMatch(player.value)
        }
    }
}

fun getRandomDiceSides(): MutableList<DiceSide> {
    return dices.map { dice ->
        val item = dice.sides[(dice.sides.indices.random())]
        val favor = dice.tokenSides.contains(item.side)
        item.copy(favor = favor)
    }.toMutableList()
}