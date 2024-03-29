package com.iago.orlog.screens.gods.commons

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iago.orlog.R
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.utils.Player

@Composable
fun Header(
    viewModel: ViewModelOrlog,
    player: MutableState<Player>,
    onPress: () -> Unit
) {

    val notIaPlayer = if (viewModel.player1.value.ia)
        viewModel.player2 else viewModel.player1

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, top = 25.dp, bottom = 10.dp, end = 25.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column() {
            Text(
                text = stringResource(notIaPlayer.value.name),
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.primary,
            )
            Text(
                text = stringResource(R.string.select_gods),
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.secondary,
            )
        }
        ButtonConfirmGods(player.value.godFavors.size === 3) {
            onPress()
        }
    }
}