package com.iago.orlog.screens.gods.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.iago.orlog.R
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.utils.God
import com.iago.orlog.utils.Player

@Composable
fun GodCard(god: God, player: MutableState<Player>, viewModel: ViewModelOrlog) {

    val active = remember { mutableStateOf(false) }
    val openDialog = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = player.value.gods) {
        active.value = player.value.gods.contains(god)
    }

    if (openDialog.value)
        GodInfo(god, openDialog)

    Column(
        modifier = Modifier
            .height(260.dp)
            .background(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colors.onBackground
            )
            .border(
                width = 2.dp,
                shape = MaterialTheme.shapes.medium,
                color = if (active.value) MaterialTheme.colors.primary else MaterialTheme.colors.secondary,
            )
            .clickable {
                if (active.value) {
                    viewModel.updateGodsList(player.value.gods, false, god, viewModel)
                } else if (player.value.gods.size < 3) {
                    viewModel.updateGodsList(player.value.gods, true, god, viewModel)
                }
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            tint = MaterialTheme.colors.secondary,
            contentDescription = stringResource(R.string.info),
            modifier = Modifier
                .padding(end = 10.dp)
                .align(Alignment.End)
                .size(18.dp)
                .clickable {
                    openDialog.value = true
                },
        )
        Image(
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxHeight(.9f)
                .fillMaxWidth(.9f)
                .padding(10.dp),
            painter = rememberAsyncImagePainter(god.img),
            contentDescription = stringResource(id = god.name),
        )
    }
}