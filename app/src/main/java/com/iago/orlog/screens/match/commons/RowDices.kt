package com.iago.orlog.screens.match.commons

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.screens.match.getRandomDiceSides
import com.iago.orlog.utils.DiceSide
import com.iago.orlog.utils.Phase
import com.iago.orlog.utils.Player
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowDices(
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
    dicesTable: MutableState<MutableList<DiceSide>>,
    player: MutableState<Player>,
    viewModel: ViewModelOrlog,
    enablePress: Boolean,
    onPressEndTurn: () -> Unit,
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp * .85f

    val padding = with(LocalDensity.current) {
        screenWidth * 0.01f
    }

    val openDialog = remember { mutableStateOf(false) }
    val diceInfo = remember { mutableStateOf<DiceSide?>(null) }

    if (openDialog.value && diceInfo.value != null)
        DiceInfo(openDialog, diceInfo)

    LaunchedEffect(key1 = player.value, key2 = enablePress) {
        selectRemainingDices(enablePress, player, dicesTable, dicesSelectedPlayer)
    }

    LaunchedEffect(key1 = viewModel.turn.value) {
        updateDices(viewModel, dicesTable, player, enablePress)
        delay(1000L)
    }

    LaunchedEffect(key1 = enablePress) {
        delay(1200L)
        iaSelectDicesAutomatic(
            viewModel,
            dicesTable,
            dicesSelectedPlayer,
            player,
            enablePress,
            onPressEndTurn
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        horizontalArrangement = if (dicesTable.value.size != 6)
            Arrangement.Center else Arrangement.SpaceBetween,
    ) {
        if (dicesTable.value.isEmpty())
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenWidth / 6)
            )
        else
            (dicesTable.value).forEachIndexed { index, item ->
                Box(
                    modifier = Modifier
                        .width(screenWidth / 6)
                        .height(screenWidth / 6)
                        .aspectRatio(1f)
                        .padding(padding)
                        .background(MaterialTheme.colors.onBackground, RoundedCornerShape(5.dp))
                        .combinedClickable(
                            onClick = {
                                if (enablePress && player.value.coinFace === viewModel.turn.value && viewModel.phase.value === Phase.ROLL_PHASE)
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
                        contentDescription = stringResource(id = item.name),
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

fun iaSelectDicesAutomatic(
    viewModel: ViewModelOrlog,
    dicesTable: MutableState<MutableList<DiceSide>>,
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
    player: MutableState<Player>,
    enablePress: Boolean,
    onPressEndTurn: () -> Unit
) {
    if (player.value.ia && viewModel.turn.value === player.value.coinFace && enablePress) {
        if (player.value.reroll > 0 && dicesTable.value.isNotEmpty())
            selectDicesAutomatic(dicesTable, dicesSelectedPlayer)
        onPressEndTurn()
    }
}

fun updateDices(
    viewModel: ViewModelOrlog,
    dicesTable: MutableState<MutableList<DiceSide>>,
    player: MutableState<Player>,
    enablePress: Boolean
) {
    if (player.value.reroll != 3 && viewModel.turn.value === player.value.coinFace && enablePress)
        dicesTable.value = getRandomDiceSides(dicesTable.value)
}

fun selectDicesAutomatic(
    dicesTable: MutableState<MutableList<DiceSide>>,
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
) {
    var randomSize = (0 until dicesTable.value.size).random()
    if (randomSize == 0) randomSize = 1
    val randomItems = dicesTable.value.shuffled().take(randomSize)
    randomItems.forEachIndexed { _, diceSide ->
        val index = dicesTable.value.indexOf(diceSide)
        selectDice(dicesSelectedPlayer, diceSide, dicesTable, index)
    }
}

fun selectRemainingDices(
    enablePress: Boolean,
    player: MutableState<Player>,
    dicesTable: MutableState<MutableList<DiceSide>>,
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
) {
    if (player.value.reroll == 0 && enablePress) {
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