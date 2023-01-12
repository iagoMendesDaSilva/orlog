package com.iago.orlog.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iago.orlog.screens.gods.GodsScreen
import com.iago.orlog.screens.coin.CoinScreen
import com.iago.orlog.screens.home.HomeScreen
import com.iago.orlog.screens.instruction.InstructionScreen
import com.iago.orlog.screens.match.MatchScreen


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

        composable(Screens.GodsScreen.name) {
            GodsScreen(navController)
        }

        composable(Screens.CoinScreen.name) {
            CoinScreen(navController)
        }
    }
}