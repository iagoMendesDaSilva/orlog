package com.iago.orlog.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iago.orlog.screens.home.HomeScreen
import com.iago.orlog.screens.match.MatchScreen
import com.iago.orlog.screens.instruction.InstructionScreen


@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.name
    ) {

        composable(Screens.HomeScreen.name) {
            HomeScreen(navController)
        }

        composable(Screens.InstructionScreen.name) {
            InstructionScreen(navController)
        }

        composable(Screens.MatchScreen.name) {
            MatchScreen(navController)
        }

    }
}