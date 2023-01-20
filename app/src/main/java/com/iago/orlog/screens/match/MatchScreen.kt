package com.iago.orlog.screens.match

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.screens.match.commons.MatchDivision
import com.iago.orlog.screens.match.commons.RowDices
import com.iago.orlog.screens.match.commons.RowSelectedDices
import com.iago.orlog.screens.match.commons.StatusMatch
import com.iago.orlog.utils.DiceSide
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
        Column(modifier = Modifier.rotate(180f)) {
            RowDices(dicesSelectedPlayer2, dicesTablePlayer2)
            RowSelectedDices(dicesSelectedPlayer2.value)
            StatusMatch(viewModel.player2.value)
        }

        MatchDivision(viewModel)

        Column() {
            RowDices(dicesSelectedPlayer1, dicesTablePlayer1)
            RowSelectedDices(dicesSelectedPlayer1.value)
            StatusMatch(viewModel.player1.value)
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