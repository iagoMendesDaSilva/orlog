package com.iago.orlog.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iago.orlog.commons.Logo
import com.iago.orlog.screens.home.commons.Header
import com.iago.orlog.screens.home.commons.InfoDesc
import com.iago.orlog.screens.home.commons.ModeButton
import com.iago.orlog.screens.home.commons.PlayButton
import com.iago.orlog.utils.GameModes
import com.iago.orlog.utils.Infos
import com.iago.orlog.utils.Mode

@Composable
fun HomeScreen(navController: NavHostController) {

    val mode = remember { mutableStateOf(GameModes.onePlayer) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Header(navController)
        Logo()
        Footer(mode)
    }
}

@Composable
fun Footer(mode: MutableState<Mode>) {
    Row(
        Modifier
            .fillMaxWidth(.7f)
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        InfoDesc(Infos.duration)
        InfoDesc(Infos.gods)
        InfoDesc(Infos.hp)

    }
    PlayButton() {}
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
