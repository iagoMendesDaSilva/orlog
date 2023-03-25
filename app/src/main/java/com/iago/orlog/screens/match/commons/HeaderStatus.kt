package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iago.orlog.utils.Phase
import com.iago.orlog.utils.Player

@Composable
fun HeaderStatus(
    player: MutableState<Player>,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            TokensInfo(player.value.tokens)
            GemsInfo(player.value.gems)
        }

    }
}