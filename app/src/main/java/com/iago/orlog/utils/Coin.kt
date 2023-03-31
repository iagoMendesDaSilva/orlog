package com.iago.orlog.utils

import com.iago.orlog.R

enum class CoinSide {
    FACE_UP,
    FACE_DOWN,
    UNDEFINED,
}

data class Coin(
    val title: Int,
    val image: Int,
    val icon: Int,
)

object Coins {
    val head =
        Coin(title = R.string.head, image = R.drawable.coin_head, icon= R.drawable.head,)
    val tail =
        Coin(title = R.string.tail, image = R.drawable.coin_tail, icon =  R.drawable.tail,)
}