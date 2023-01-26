package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iago.orlog.utils.BoardDice
import com.iago.orlog.utils.BoardDices
import com.iago.orlog.utils.DiceSide
import com.iago.orlog.utils.dices

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowDices(
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
    dicesTable: MutableState<BoardDices>
) {

    val openDialog = remember { mutableStateOf(false) }
    val diceInfo = remember { mutableStateOf<BoardDice?>(null) }

    if (openDialog.value && diceInfo.value != null)
        DiceInfo(openDialog, diceInfo)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .height(45.dp),
        horizontalArrangement = if (dicesTable.value.diceSides.size != 6) Arrangement.Center else Arrangement.SpaceBetween,
    ) {
        (dicesTable.value.diceSides).forEachIndexed { index, item ->
            Image(
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(55.dp)
                    .height(55.dp)
                    .padding(horizontal = 3.dp)
                    .combinedClickable(
                        onClick = { selectDice(dicesSelectedPlayer, item, dicesTable, index) },
                        onLongClick = {
                            diceInfo.value = BoardDice(
                                dice = dices[dicesTable.value.positions[index]],
                                diceSide = item
                            )
                            openDialog.value = true
                        }
                    ),
                painter = painterResource(if (item.favor) item.imgFavor else item.img),
            )

        }
    }
}

fun selectDice(
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
    item: DiceSide,
    dicesTable: MutableState<BoardDices>,
    index: Int
) {
    with(dicesSelectedPlayer) {
        value = (value + item.copy()).toMutableList()
    }
    removeDice(dicesTable, item, index)
}

fun removeDice(dicesTable: MutableState<BoardDices>, item: DiceSide, index: Int) {
    val positions = dicesTable.value.positions.filterIndexed { i, _ -> i != index }.toMutableList()
    val diceSides = dicesTable.value.diceSides.filterIndexed { i, _ -> i != index }.toMutableList()

    with(dicesTable) {
        value = BoardDices(
            positions = positions,
            diceSides = diceSides,
        )
    }
}