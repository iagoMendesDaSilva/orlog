package com.iago.orlog.navigation

enum class Screens {
    InstructionScreen,
    HomeScreen,
    MatchScreen;

    companion object {
        fun fromRoute(route: String?): Screens = when (route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            MatchScreen.name -> MatchScreen
            InstructionScreen.name->InstructionScreen
            null -> HomeScreen
            else -> throw  IllegalArgumentException("Route $route is not recognized")
        }
    }

}