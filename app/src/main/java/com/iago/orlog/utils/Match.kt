package com.iago.orlog.utils


data class Player(
    val hp: Int,
    val name: Int,
    val gods: MutableList<God>,
    val coinFace: COIN,
) {}

enum class COIN  {
    FACE_UP,
    FACE_DOWN,
    UNDEFINED,
}

class Match(
    var mode: MODES,
    var turn: COIN,
    var player1: Player,
    val player2: Player,
){}