package com.iago.orlog.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.SwitchAccount
import androidx.compose.ui.graphics.vector.ImageVector
import com.iago.orlog.R

object Infos {
    val duration =
        Info(title = R.string.duration_info, icon = Icons.Default.HourglassBottom)
    val gems =
        Info(title = R.string.gems_info, icon = Icons.Default.Favorite)
    val godFavors =
        Info(title = R.string.god_favors_info, icon = Icons.Default.AccountBox)
}

data class Info(
    val title: Int,
    val icon: ImageVector,
)