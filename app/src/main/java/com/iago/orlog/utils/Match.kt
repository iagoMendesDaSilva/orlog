package com.iago.orlog.utils

import com.iago.orlog.R

data class DiceSide(
    val name: Int,
    val img: Int,
    val imgFavor: Int,
    val side: DICE_FACE,
    var favor: Boolean,
)

data class Dice(
    val sides: List<DiceSide>,
    val tokenSides: List<DICE_FACE>,
)

data class Player(
    val hp: Int,
    val name: Int,
    val gods: MutableList<God>,
    val coinFace: COIN,
    val tokens: Int,
)

enum class DICE_FACE {
    HELMET,
    ARROW,
    SHIELD,
    AXE,
    HAND,
}

enum class COIN {
    FACE_UP,
    FACE_DOWN,
    UNDEFINED,
}

class Match(
    var mode: MODES,
    var turn: COIN,
    var player1: Player,
    val player2: Player,
) {}

val diceSides = listOf<DiceSide>(
    DiceSide(
        name = R.string.shield,
        side = DICE_FACE.SHIELD,
        img = R.drawable.shield,
        imgFavor = R.drawable.shield_favor,
        favor = false,
    ),
    DiceSide(
        name = R.string.hand,
        side = DICE_FACE.HAND,
        img = R.drawable.hand,
        imgFavor = R.drawable.hand_favor,
        favor = false,
    ),
    DiceSide(
        name = R.string.arrow,
        side = DICE_FACE.ARROW,
        img = R.drawable.arrow,
        imgFavor = R.drawable.arrow_favor,
        favor = false,
    ),
    DiceSide(
        name = R.string.axe,
        side = DICE_FACE.AXE,
        img = R.drawable.axe,
        imgFavor = R.drawable.axe_favor,
        favor = false,
    ),
    DiceSide(
        name = R.string.helmet,
        side = DICE_FACE.HELMET,
        img = R.drawable.helmet,
        imgFavor = R.drawable.helmet_favor,
        favor = false,
    ),
    DiceSide(
        name = R.string.axe,
        side = DICE_FACE.AXE,
        img = R.drawable.axe,
        imgFavor = R.drawable.axe_favor,
        favor = false,
    ),
)

val dices = mutableListOf<Dice>(
    Dice(
        sides = diceSides,
        tokenSides = listOf(
            DICE_FACE.ARROW,
            DICE_FACE.HELMET,
        ),
    ),
    Dice(
        sides = diceSides,
        tokenSides = listOf(
            DICE_FACE.SHIELD,
            DICE_FACE.HELMET,
        ),
    ),
    Dice(
        sides = diceSides,
        tokenSides = listOf(
            DICE_FACE.HAND,
            DICE_FACE.HELMET,
        ),
    ),
    Dice(
        sides = diceSides,
        tokenSides = listOf(
            DICE_FACE.SHIELD,
            DICE_FACE.ARROW,
        ),
    ),
    Dice(
        sides = diceSides,
        tokenSides = listOf(
            DICE_FACE.SHIELD,
            DICE_FACE.HAND,
        ),
    ),
    Dice(
        sides = diceSides,
        tokenSides = listOf(
            DICE_FACE.HAND,
            DICE_FACE.ARROW,
        ),
    ),
)