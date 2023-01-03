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
    val hp =
        Info(value = R.string.hp_value, title = R.string.hp, icon = Icons.Default.Favorite)
    val gods =
        Info(value = R.string.gods_value, title = R.string.gods, icon = Icons.Default.SwitchAccount)
}

data class Info(
    val title: Int,
    val value: Int,
    val icon: ImageVector,
)