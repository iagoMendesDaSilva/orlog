package com.iago.orlog.screens.home.commons

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.navigation.Screens
import com.iago.orlog.utils.GameModes
import com.iago.orlog.utils.Infos
import com.iago.orlog.utils.Mode

@Composable
fun Footer(
    mode: MutableState<Mode>,
    navController: NavHostController,
    viewModel: ViewModelOrlog,
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            Modifier
                .fillMaxWidth(.7f)
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            InfoDesc(Infos.duration)
            InfoDesc(Infos.godFavors)
            InfoDesc(Infos.gems)

        }
        PlayButton() {
            viewModel.updateMode(mode.value.mode)
            navController.navigate(Screens.CoinScreen.name)
        }
        Row(
            Modifier
                .fillMaxWidth(.9f)
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ModeButton(GameModes.onePlayer, mode)
            ModeButton(GameModes.twoPlayer, mode)
            ModeButton(GameModes.online, mode, true)
        }
    }
}