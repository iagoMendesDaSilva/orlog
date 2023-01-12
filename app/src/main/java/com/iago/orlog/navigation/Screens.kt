package com.iago.orlog.navigation

enum class Screens {
    InstructionScreen,
    HomeScreen,
    GodsScreen,
    CoinScreen,
    MatchScreen;

    companion object {
        fun fromRoute(route: String?): Screens = when (route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            MatchScreen.name -> MatchScreen
            CoinScreen.name->CoinScreen
            GodsScreen.name -> GodsScreen
            InstructionScreen.name->InstructionScreen
            null -> HomeScreen
            else -> throw  IllegalArgumentException("Route $route is not recognized")
        }
    }

}