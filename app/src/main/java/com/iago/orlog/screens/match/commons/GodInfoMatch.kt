package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.iago.orlog.R
import com.iago.orlog.utils.Favor
import com.iago.orlog.utils.God

@Composable
fun GodInfoMatch(
    god: God,
    openDialog: MutableState<Boolean>,
    chooseGodFavor: (godFavor: Favor) -> Unit
) {
    val godFavor = remember { mutableStateOf<Favor?>(null) }

    Dialog(
        onDismissRequest = { openDialog.value = false },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colors.onBackground,
                        shape = MaterialTheme.shapes.medium,
                    )
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                    Header(god, openDialog)
                    Favors(god, godFavor)
                    ButtonChoose(godFavor, chooseGodFavor, openDialog)
            }
        },
    )
}

@Composable
fun ButtonChoose(
    godFavor: MutableState<Favor?>,
    chooseGodFavor: (godFavor: Favor) -> Unit,
    openDialog: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(.9f)
            .padding(vertical = 15.dp)
            .background(
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colors.onBackground,
            )
            .border(
                width = 1.dp,
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colors.secondary,
            )
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .clickable {
                if (godFavor.value != null) {
                    chooseGodFavor(godFavor.value!!)
                    openDialog.value = false
                }

            }
            .alpha(if (godFavor.value == null) .5f else 1f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.choose),
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.body2,
        )
    }
}

@Composable
fun Header(god: God, openDialog: MutableState<Boolean>) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            contentDescription = null,
            imageVector = Icons.Outlined.Close,
            tint = MaterialTheme.colors.secondary,
            modifier = Modifier
                .align(Alignment.End)
                .padding(bottom = 10.dp)
                .clickable { openDialog.value = false },
        )
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(god.name),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.secondary,
        )
        Text(
            modifier = Modifier.padding(top = 5.dp),
            textAlign = TextAlign.Center,
            text = stringResource(god.description),
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.secondary,
        )
    }
}

@Composable
fun Favors(god: God, godFavor: MutableState<Favor?>) {
    Text(
        textAlign = TextAlign.End,
        style = MaterialTheme.typography.subtitle2,
        modifier = Modifier
            .fillMaxWidth(.9f)
            .padding(top = 10.dp),
        color = MaterialTheme.colors.secondaryVariant,
        text = stringResource(R.string.priority, god.priority),
    )
    god.favors.forEach { favor ->
        FavorItem(favor, godFavor)
    }
}

@Composable
fun FavorItem(favor: Favor, godFavor: MutableState<Favor?>) {

    val active = godFavor.value === favor

    Row(
        modifier = Modifier
            .fillMaxWidth(.9f)
            .padding(vertical = 8.dp)
            .background(
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colors.onBackground,
            )
            .border(
                width = 1.dp,
                shape = MaterialTheme.shapes.small,
                color = if (active) MaterialTheme.colors.primary else MaterialTheme.colors.secondary,
            )
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .clickable {
                godFavor.value = if (active) null else favor
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.width(30.dp),
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary,
            text = favor.cost.toString(),
        )
        Text(
            modifier = Modifier.width(30.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary,
            text = stringResource(R.string.symbol),
        )
        Text(
            maxLines=1,
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary,
            text = stringResource(favor.description, favor.effect.toInt())
        )
    }
}