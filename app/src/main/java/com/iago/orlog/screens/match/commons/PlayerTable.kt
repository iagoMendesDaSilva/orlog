package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.utils.*


@Composable
fun PlayerTable(
    modifier: Modifier,
    viewModel: ViewModelOrlog,
    player: MutableState<Player>,
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
    dicesTablePlayer: MutableState<MutableList<DiceSide>>,
    enablePress: Boolean,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        HeaderStatus(player)
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            RowDices(dicesSelectedPlayer, dicesTablePlayer, player, viewModel, enablePress) {
                endTurn(viewModel, player)
            }
            RowSelectedDices(dicesSelectedPlayer.value)
            FooterStatus(player, viewModel, enablePress,
                onPressEndTurn = { endTurn(viewModel, player) },
                pressGodFavor = { godFavor, favor ->
                    chooseGodFavor(
                        godFavor,
                        favor,
                        viewModel,
                        player,
                    )
                })
        }
    }
}

fun endTurn(viewModel: ViewModelOrlog, player: MutableState<Player>) {
    if (viewModel.phase.value == Phase.ROLL_PHASE)
        viewModel.updatePlayer("reroll", player.value.reroll - 1, player)
    if (viewModel.phase.value == Phase.GOD_FAVOR_PHASE)
        if (player.value.favorResolution == null)
            viewModel.updatePlayer("favorResolution", FavorResolution(null, null), player)
    viewModel.changeTurn()
}

fun chooseGodFavor(
    godFavor: God,
    favor: Favor,
    viewModel: ViewModelOrlog,
    player: MutableState<Player>
) {
    viewModel.updatePlayer("favorResolution", FavorResolution(favor, godFavor.id), player)
    endTurn(viewModel, player)
}