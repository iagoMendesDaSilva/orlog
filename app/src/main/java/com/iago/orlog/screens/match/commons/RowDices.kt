@file:Suppress("DEPRECATED_IDENTITY_EQUALS")

package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.screens.match.getRandomDiceSides
import com.iago.orlog.utils.DiceSide
import com.iago.orlog.utils.MODES
import com.iago.orlog.utils.Phase
import com.iago.orlog.utils.Player
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowDices(
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
    dicesTable: MutableState<MutableList<DiceSide>>,
    player: MutableState<Player>,
    viewModel: ViewModelOrlog,
    onPressEndTurn: () -> Unit,
) {

    val openDialog = remember { mutableStateOf(false) }
    val diceInfo = remember { mutableStateOf<DiceSide?>(null) }

    if (openDialog.value && diceInfo.value != null)
        DiceInfo(openDialog, diceInfo)

    LaunchedEffect(key1 = player.value) {
        selectRemainingDices(player, dicesTable, dicesSelectedPlayer)
    }

    LaunchedEffect(key1 = viewModel.turn.value) {
        if (viewModel.turn.value === player.value.coinFace && player.value.reroll != 3)
            dicesTable.value = getRandomDiceSides(dicesTable.value)

        if (player.value.ia && viewModel.mode.value === MODES.ONE_PLAYER && player.value.coinFace === viewModel.turn.value) {
            if (player.value.reroll > 0 && dicesTable.value.isNotEmpty())
                selectDicesAutomatic(dicesTable, dicesSelectedPlayer)
            onPressEndTurn()
        }

    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .height(45.dp),
        horizontalArrangement = if (dicesTable.value.size != 6) Arrangement.Center else Arrangement.SpaceBetween,
    ) {
        (dicesTable.value).forEachIndexed { index, item ->
            Box(
                modifier = Modifier
                    .width(55.dp)
                    .height(55.dp)
                    .padding(horizontal = 3.dp)
                    .background(MaterialTheme.colors.onBackground, RoundedCornerShape(5.dp))
                    .combinedClickable(
                        onClick = {
                            if (player.value.coinFace === viewModel.turn.value && viewModel.phase.value === Phase.ROLL_PHASE)
                                selectDice(dicesSelectedPlayer, item, dicesTable, index)
                        },
                        onLongClick = {
                            diceInfo.value = item
                            openDialog.value = true
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (item.favor)
                    DashedStroke()
                Image(
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(5.dp)),
                    painter = painterResource(item.img),
                )
            }
        }
    }
}

fun selectDicesAutomatic(
    dicesTable: MutableState<MutableList<DiceSide>>,
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
) {
    var randomSize = (0 until dicesTable.value.size).random()
    if (randomSize === 0) randomSize = 1
    val randomItems = dicesTable.value.shuffled().take(randomSize)
    randomItems.forEachIndexed { _, diceSide ->
        val index = dicesTable.value.indexOf(diceSide)
        selectDice(dicesSelectedPlayer, diceSide, dicesTable, index)
    }
}

fun selectRemainingDices(
    player: MutableState<Player>,
    dicesTable: MutableState<MutableList<DiceSide>>,
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
) {
    if (player.value.reroll == 0) {
        dicesTable.value.forEachIndexed { index, item ->
            selectDice(dicesSelectedPlayer, item, dicesTable, index)
        }
        dicesTable.value = mutableListOf()
    }
}

fun selectDice(
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
    item: DiceSide,
    dicesTable: MutableState<MutableList<DiceSide>>,
    index: Int
) {
    with(dicesSelectedPlayer) {
        value = (value + item.copy()).toMutableList()
    }
    removeDice(dicesTable, index)
}

fun removeDice(dicesTable: MutableState<MutableList<DiceSide>>, index: Int) {
    val dicesTableFiltered = dicesTable.value.filterIndexed { i, _ -> i != index }.toMutableList()

    with(dicesTable) {
        value = dicesTableFiltered
    }
}