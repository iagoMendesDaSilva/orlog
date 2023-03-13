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
import com.iago.orlog.utils.Favor
import com.iago.orlog.utils.God
import com.iago.orlog.utils.Phase
import com.iago.orlog.utils.Player

@Composable
fun FooterStatus(
    player: MutableState<Player>,
    viewModel: ViewModelOrlog,
    onPressEndTurn: ()->Unit,
    pressGodFavor: (god: God, favor: Favor) -> Unit
) {
    Row {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(end = 10.dp, top = 10.dp)
        ) {
            EndTurnButton(true) {
              if(viewModel.phase.value === Phase.ROLL_PHASE && viewModel.turn.value === player.value.coinFace)
                  onPressEndTurn()
            }
        }
        GodsList(
            viewModel,
            player.value.godFavors,
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 5.dp),
            pressGodFavor,
        )
    }
}