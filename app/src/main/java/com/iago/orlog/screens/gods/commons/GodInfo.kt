package com.iago.orlog.screens.gods.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.iago.orlog.R
import com.iago.orlog.utils.Favor
import com.iago.orlog.utils.God


@Composable
fun GodInfo(god: God, openDialog: MutableState<Boolean>) {
    Dialog(
        onDismissRequest = { openDialog.value = false },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .fillMaxHeight(.9f)
                    .background(
                        color = MaterialTheme.colors.onBackground,
                        shape = MaterialTheme.shapes.medium,
                    )
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Header(god, openDialog)
                Image(
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .fillMaxHeight(.7f),
                    painter = painterResource(god.img),
                    contentDescription = stringResource(id = god.name)
                )
                Footer(god)
            }
        },
    )
}

@Composable
fun Header(god: God, openDialog: MutableState<Boolean>) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Icon(
            contentDescription = stringResource(id = R.string.close),
            imageVector = Icons.Outlined.Close,
            tint = MaterialTheme.colors.secondary,
            modifier = Modifier
                .align(Alignment.End)
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
fun Footer(god: God) {
    Text(
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h2,
        color = MaterialTheme.colors.secondary,
        text = stringResource(R.string.priority, god.priority),
    )
    Line()
    god.favors.forEach { favor ->
        FavorItem(favor)
    }
    Line()
}

@Composable
fun FavorItem(favor: Favor) {
    Row() {
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
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary,
            text = stringResource(favor.description, favor.effect.toInt())
        )
    }
}

@Composable
fun Line() {
    Box(
        modifier = Modifier
            .fillMaxWidth(9f)
            .padding(vertical = 7.dp)
            .height(1.dp)
            .background(MaterialTheme.colors.secondary)
    )
}