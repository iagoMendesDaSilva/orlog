package com.iago.orlog.utils

import android.os.Parcelable
import com.iago.orlog.R
import kotlinx.android.parcel.Parcelize

object GameModes {
    val onePlayer =
        Mode(name = R.string.one_player, mode = MODES.ONE_PLAYER, icon = R.drawable.axe_single)
    val online = Mode(name = R.string.online, mode = MODES.ONLINE, icon = R.drawable.axe_double)
}

@Parcelize
data class Mode(
    val mode: MODES,
    val name: Int,
    val icon: Int,
) : Parcelable

enum class MODES {
    ONE_PLAYER,
    ONLINE,
}