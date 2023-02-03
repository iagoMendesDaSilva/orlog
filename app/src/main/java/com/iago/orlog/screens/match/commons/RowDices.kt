package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.PathEffect.Companion.dashPathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iago.orlog.utils.DiceSide

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowDices(
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
    dicesTable: MutableState<MutableList<DiceSide>>
) {

    val openDialog = remember { mutableStateOf(false) }
    val diceInfo = remember { mutableStateOf<DiceSide?>(null) }

    if (openDialog.value && diceInfo.value != null)
        DiceInfo(openDialog, diceInfo)

    val strokeColor = MaterialTheme.colors.primary
    val strokeDashed =
        Stroke(width = 1.5f, pathEffect = dashPathEffect(floatArrayOf(10f, 10f), 0f))

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
                        onClick = { selectDice(dicesSelectedPlayer, item, dicesTable, index) },
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

fun selectDice(
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
    item: DiceSide,
    dicesTable: MutableState<MutableList<DiceSide>>,
    index: Int
) {
    with(dicesSelectedPlayer) {
        value = (value + item.copy()).toMutableList()
    }
    removeDice(dicesTable, item, index)
}

fun removeDice(dicesTable: MutableState<MutableList<DiceSide>>, item: DiceSide, index: Int) {
    val dicesTableFiltered = dicesTable.value.filterIndexed { i, _ -> i != index }.toMutableList()

    with(dicesTable) {
        value = dicesTableFiltered
    }
}