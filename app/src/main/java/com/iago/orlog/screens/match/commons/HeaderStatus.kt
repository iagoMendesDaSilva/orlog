package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarms
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.screens.match.getRandomDiceSides
import com.iago.orlog.utils.DiceSide
import com.iago.orlog.utils.Player

@Composable
fun HeaderStatus(
    player: MutableState<Player>,
    dicesTablePlayer: MutableState<MutableList<DiceSide>>,
    viewModel: ViewModelOrlog
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            TokensInfo(player.value.gems)
            GemsInfo(player.value.tokens)
        }
        Reroll(player, dicesTablePlayer.value) {
            viewModel.updatePlayer("reroll", player.value.reroll - 1, player)
            dicesTablePlayer.value = getRandomDiceSides(dicesTablePlayer.value)
        }

    }
}