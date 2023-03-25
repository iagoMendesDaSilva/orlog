package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iago.orlog.utils.Player
import com.iago.orlog.utils.StatusInfoMatch

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
            StatusMatchInfo(player.value.tokens, StatusInfoMatch.TOKENS)
            StatusMatchInfo(player.value.gems, StatusInfoMatch.GEMS)
        }

    }
}