package com.iago.orlog.utils


enum class StatusInfoMatch {
    GEMS,
    TOKENS,
}

enum class Phase {
    ROLL_PHASE,
    GOD_FAVOR_PHASE,
    RESOLUTION_PHASE,
}

data class FavorConfig(
    val useBeforeResolution: Boolean,
    val method: ()->Unit,
)

data class Player(
    val ia: Boolean,
    val favorResolution: FavorResolution?,
    val gems: Int,
    val name: Int,
    val godFavors: MutableList<God>,
    val coinFace: CoinSide,
    val tokens: Int,
    val reroll: Int,
)