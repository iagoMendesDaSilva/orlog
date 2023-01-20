package com.iago.orlog.screens.match.commons

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iago.orlog.utils.DiceSide

@Composable
fun RowDices(
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
    dicesTable: MutableState<MutableList<DiceSide>>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .height(45.dp),
        horizontalArrangement = if (dicesTable.value.size != 6) Arrangement.Center else Arrangement.SpaceBetween,
    ) {
        (dicesTable.value).forEachIndexed { _, item ->
            Image(
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(55.dp)
                    .height(55.dp)
                    .padding(horizontal = 3.dp)
                    .clickable {
                        selectDice(dicesSelectedPlayer, item, dicesTable)
                    },
                painter = painterResource(if (item.favor) item.imgFavor else item.img),
            )

        }
    }
}

fun selectDice(
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
    item: DiceSide,
    dicesTable: MutableState<MutableList<DiceSide>>
) {
    with(dicesSelectedPlayer) {
        value = (value + item.copy()).toMutableList()
    }
    removeDice(dicesTable, item)
}

fun removeDice(dicesTable: MutableState<MutableList<DiceSide>>, item: DiceSide) {
    with(dicesTable) {
        value = mutableListOf<DiceSide>().apply {
            addAll(value)
            remove(item)
        }
    }
}