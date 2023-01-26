package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.iago.orlog.R
import com.iago.orlog.utils.BoardDice
import com.iago.orlog.utils.DiceSide

@Composable
fun DiceInfo(openDialog: MutableState<Boolean>, diceBoard: MutableState<BoardDice?>) {
    Dialog(
        onDismissRequest = {
            openDialog.value = false
        },
        content = {
            Column(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.onBackground,
                        shape = MaterialTheme.shapes.medium,
                    )
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Header(text = diceBoard.value!!.diceSide!!.description)
                DiceDraw(diceBoard)
                Footer(diceBoard)
            }
        },
    )
}

@Composable
fun Header(text: Int) {
    Text(
        text = stringResource(text),
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.secondary,
    )
}

@Composable
fun Footer(diceBoard: MutableState<BoardDice?>) {
    Row() {
        Text(
            text = stringResource(
                R.string.gain_favor,
                if (diceBoard.value!!.diceSide.favor) 1 else 0
            ),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary,
        )
        Text(
            text = " ${stringResource(R.string.symbol)}",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary,
        )
    }
}

@Composable
fun DiceDraw(diceBoard: MutableState<BoardDice?>) {

    val sides = diceBoard.value!!.dice.sides
    val side = diceBoard.value!!.diceSide.side
    val favorSides = diceBoard.value!!.dice.tokenSides

    Column(
        modifier = Modifier.padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageDiceSide(sides[0], side === sides[0].side, favorSides.contains(sides[0].side))
        Row() {
            sides.drop(1).forEachIndexed { index, it ->
                if (index < 3)
                    ImageDiceSide(it, side === it.side, favorSides.contains(it.side))
            }
        }
        sides.drop(4).forEach {
            ImageDiceSide(it, side === it.side, favorSides.contains(it.side))
        }
    }
}

@Composable
fun ImageDiceSide(diceSide: DiceSide, active: Boolean, favor: Boolean) {
    Image(
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .width(55.dp)
            .height(55.dp)
            .padding(5.dp)
            .alpha(if (active) 1f else .5f),
        painter = painterResource(if (favor) diceSide.imgFavor else diceSide.img),
    )
}