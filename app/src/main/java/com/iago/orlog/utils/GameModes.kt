package com.iago.orlog.utils

import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.iago.orlog.R

object GameModes {
    val onePlayer =
        Mode(name = R.string.one_player, mode = MODES.ONE_PLAYER, icon = Default.Person)
    val twoPlayer =
        Mode(name = R.string.two_player, mode = MODES.TWO_PLAYER, icon = Default.Group)
    val online = Mode(name = R.string.online, mode = MODES.ONLINE, icon = Default.PersonSearch)
}

data class Mode(
    val mode: MODES,
    val name: Int,
    val icon: ImageVector,
)

enum class MODES {
    ONE_PLAYER,
    TWO_PLAYER,
    ONLINE,
}