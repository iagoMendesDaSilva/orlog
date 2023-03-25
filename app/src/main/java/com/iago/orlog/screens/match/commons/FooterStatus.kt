package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
    enablePress: Boolean,
    onPressEndTurn: () -> Unit,
    pressGodFavor: (god: God, favor: Favor) -> Unit
) {

    LaunchedEffect(key1 = viewModel.phase.value, block = {
        iaSelectGodFavorAutomatic(
            player.value,
            enablePress,
            viewModel,
            pressGodFavor,
            onPressEndTurn
        )
    })

    Row {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(end = 10.dp, top = 10.dp)
        ) {
            EndTurnButton(true) {
                if (viewModel.phase.value != Phase.RESOLUTION_PHASE && viewModel.turn.value === player.value.coinFace)
                    onPressEndTurn()
            }
        }
        GodsList(
            enablePress = enablePress && viewModel.phase.value === Phase.GOD_FAVOR_PHASE,
            player.value,
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .aspectRatio(2f)
                .padding(vertical = 5.dp),
            pressGodFavor,
        )
    }
}

fun getAffordableGods(godFavors: List<God>, tokens: Int): List<God> {
    return godFavors.filter { god ->
        god.favors.any { favor ->
            favor.cost <= tokens
        }
    }
}

fun getAffordableFavors(favors: List<Favor>, tokens: Int): List<Favor> {
    return favors.filter { it.cost <= tokens }.sortedByDescending { it.effect }
}

fun iaSelectGodFavorAutomatic(
    player: Player,
    enablePress: Boolean,
    viewModel: ViewModelOrlog,
    pressGodFavor: (god: God, favor: Favor) -> Unit,
    onPressEndTurn: () -> Unit
) {
    if (player.ia && viewModel.turn.value === player.coinFace && viewModel.phase.value == Phase.GOD_FAVOR_PHASE && enablePress) {
        val affordableGods = getAffordableGods(player.godFavors, player.tokens)

        val selectedGod =
            if (affordableGods.isNotEmpty()) affordableGods.random()
            else player.godFavors.random()

        val affordableFavors = getAffordableFavors(selectedGod.favors, player.tokens)
        if (affordableFavors.isNotEmpty()) {
            val mostPowerfulFavor = affordableFavors.first()
            pressGodFavor(selectedGod, mostPowerfulFavor)
        } else onPressEndTurn()
    }
}