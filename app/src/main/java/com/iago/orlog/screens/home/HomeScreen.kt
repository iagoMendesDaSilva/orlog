package com.iago.orlog.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 15.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Title()
            Spacer(modifier = Modifier.height(40.dp))
            Notification()
        }
        Logo(125.dp, MaterialTheme.colors.primary)
        InfoMenu()
        ListModes(navController, viewModel)
    }

    InstructionsButton(navController)
}