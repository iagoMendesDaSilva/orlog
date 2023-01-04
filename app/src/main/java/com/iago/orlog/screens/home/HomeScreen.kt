package com.iago.orlog.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.iago.orlog.commons.Logo
import com.iago.orlog.screens.home.commons.Footer
import com.iago.orlog.screens.home.commons.Header
import com.iago.orlog.utils.GameModes

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
        Footer(mode, navController)
    }
}