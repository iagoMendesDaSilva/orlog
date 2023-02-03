package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iago.orlog.utils.Player
import com.iago.orlog.utils.gods

@Composable
fun FooterStatus(player: Player, rotate: Boolean) {
    Row {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(end = 10.dp, top = 10.dp)
        ) {
            EndTurnButton(true,) {}
        }
        GodsList(
            rotate,
            player.godFavors,
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 5.dp),
        )
    }
}