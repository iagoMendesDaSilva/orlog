package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iago.orlog.utils.Favor
import com.iago.orlog.utils.God
import com.iago.orlog.utils.Player
import com.iago.orlog.utils.gods

@Composable
fun GodsList(
    enablePress: Boolean,
    player: Player,
    modifier: Modifier,
    pressGodFavor: (god: God, favor: Favor) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End,
    ) {
        player.godFavors.forEach { godFavor ->

            val openDialog = remember { mutableStateOf(false) }

            if (openDialog.value)
                GodInfoMatch(godFavor, openDialog, player.tokens) {
                    pressGodFavor(godFavor, it)
                }

            Image(
                contentDescription = stringResource(id = godFavor.name),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(horizontal = 3.dp)
                    .clickable {
                        if (enablePress)
                            openDialog.value = true
                    },
                painter = painterResource(godFavor.img),
            )
        }
    }
}

