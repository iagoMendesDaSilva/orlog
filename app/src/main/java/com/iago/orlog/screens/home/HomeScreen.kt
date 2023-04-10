package com.iago.orlog.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.commons.Logo
import com.iago.orlog.screens.home.commons.*
import com.iago.orlog.ui.theme.Black
import com.iago.orlog.ui.theme.DarkGray

@Composable
fun HomeScreen(navController: NavHostController, viewModel: ViewModelOrlog) {

    val systemUiController = rememberSystemUiController()

    DisposableEffect(Unit) {
        systemUiController.setStatusBarColor(color = DarkGray)
        onDispose {
            systemUiController.setStatusBarColor(color = Black)
        }
    }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    val padding = with(LocalDensity.current) {
        screenHeight * 0.02f
    }

    val logoSize = with(LocalDensity.current) {
        screenHeight * 0.15f
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 15.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Title()
            Spacer(modifier = Modifier.height(padding))
            Notification()
        }
        Spacer(modifier = Modifier.height(padding * 2))
        Logo(logoSize, MaterialTheme.colors.primary)
        Spacer(modifier = Modifier.height(padding * 2))
        InfoMenu()
        ListModes(navController, viewModel)
    }
    InstructionsButton(navController)
}