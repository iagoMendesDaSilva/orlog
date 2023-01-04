package com.iago.orlog.screens.home.commons

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iago.orlog.navigation.Screens
import com.iago.orlog.utils.GameModes
import com.iago.orlog.utils.Infos
import com.iago.orlog.utils.Match
import com.iago.orlog.utils.Mode

@Composable
fun Footer(mode: MutableState<Mode>, navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            Modifier
                .fillMaxWidth(.7f)
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            InfoDesc(Infos.duration)
            InfoDesc(Infos.gods)
            InfoDesc(Infos.hp)

        }
        PlayButton() {
            Match.mode = mode.value.mode
            navController.navigate(Screens.MatchScreen.name)
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