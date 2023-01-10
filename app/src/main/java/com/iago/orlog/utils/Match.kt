package com.iago.orlog.utils

class Player(
    val hp: Int,
    val gods: List<God>,
    val coinFace: COIN,
) {}

enum class COIN {
    FACE_UP,
    FACE_DOWN,
    UNDEFINED,
}

object Match {
    var mode: MODES = MODES.ONE_PLAYER
    val turn: COIN = COIN.FACE_UP
    val player1: Player = Player(
        hp = 15,
        gods = emptyList(),
        coinFace = COIN.UNDEFINED,
    )
    val player2: Player = Player(
        hp = 15,
        gods = emptyList(),
        coinFace = COIN.UNDEFINED,
    )
}