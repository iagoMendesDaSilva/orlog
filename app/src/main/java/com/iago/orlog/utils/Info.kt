package com.iago.orlog.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.SwitchAccount
import androidx.compose.ui.graphics.vector.ImageVector
import com.iago.orlog.R

object Infos {
    val duration =
        Info(
            value = R.string.duration_value,
            title = R.string.duration,
            icon = Icons.Default.HourglassBottom
        )
    val gems =
        Info(value = R.string.gems_value, title = R.string.gems, icon = Icons.Default.Favorite)
    val godFavors =
        Info(value = R.string.god_favors_value, title = R.string.god_favors, icon = Icons.Default.SwitchAccount)
}

data class Info(
    val title: Int,
    val value: Int,
    val icon: ImageVector,
)