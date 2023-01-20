package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.iago.orlog.R
import com.iago.orlog.utils.Player

@Composable
fun StatusMatch(player: Player) {
    Row {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 6.dp)
        ) {
            Points(player.tokens, R.drawable.token, R.string.tokens)
            Points(player.hp, R.drawable.gem, R.string.gems)
        }
        GodsList(
            player.gods,
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 5.dp),
        )
    }
}