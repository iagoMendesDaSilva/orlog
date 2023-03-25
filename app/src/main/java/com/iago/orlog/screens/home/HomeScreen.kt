package com.iago.orlog.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.commons.Logo
import com.iago.orlog.screens.home.commons.*
import com.iago.orlog.utils.Mode

@Composable
fun HomeScreen(navController: NavHostController, viewModel: ViewModelOrlog) {

    Column(
        modifier = Modifier.fillMaxSize().padding(bottom = 15.dp),
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