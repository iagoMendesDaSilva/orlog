package com.iago.orlog.utils

import android.os.Parcelable
import com.iago.orlog.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Mode(
    val enabled: Boolean,
    val mode: MODES,
    val name: Int,
    val icon: Int,
) : Parcelable

enum class MODES {
    ONE_PLAYER,
    ONLINE_FRIEND,
    ONLINE_RANDOM,
}

val gameModes = mutableListOf<Mode>(
    Mode(
        name = R.string.online_friend,
        mode = MODES.ONLINE_FRIEND,
        icon = R.drawable.match_friend,
        enabled = false,
    ),
    Mode(
        name = R.string.one_player,
        mode = MODES.ONE_PLAYER,
        icon = R.drawable.match_single,
        enabled = true,
    ),
    Mode(
        name = R.string.online_random,
        mode = MODES.ONLINE_RANDOM,
        icon = R.drawable.match_random,
        enabled = false,
    ),
)