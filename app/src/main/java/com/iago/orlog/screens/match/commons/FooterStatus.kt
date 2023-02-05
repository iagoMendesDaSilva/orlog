package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.utils.Player

@Composable
fun FooterStatus(player: MutableState<Player>, rotate: Boolean, viewModel: ViewModelOrlog) {
    Row {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(end = 10.dp, top = 10.dp)
        ) {
            EndTurnButton(true,) {
                viewModel.changeTurn()
                viewModel.updatePlayer("reroll", player.value.reroll - 1, player)
            }
        }
        GodsList(
            rotate,
            player.value.godFavors,
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 5.dp),
        )
    }
}