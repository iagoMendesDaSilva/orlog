package com.iago.orlog.utils

import com.iago.orlog.R

enum class Coin {
    FACE_UP,
    FACE_DOWN,
    UNDEFINED,
}

object Coins {
    val head =
        CoinHeadTail(title = R.string.head, image = R.drawable.coin_face, icon= R.drawable.head,)
    val tail =
        CoinHeadTail(title = R.string.tail, image =R.drawable.coin_no_face, icon =  R.drawable.head,)
}

data class CoinHeadTail(
    val title: Int,
    val image: Int,
    val icon: Int,
)

enum class Phase {
    ROLL_PHASE,
    GOD_FAVOR_PHASE,
    RESOLUTION_PHASE,
}

data class Player(
    val ia: Boolean,
    val favorResolution: FavorResolution?,
    val gems: Int,
    val name: Int,
    val godFavors: MutableList<God>,
    val coinFace: Coin,
    val tokens: Int,
    val reroll: Int,
)

enum class DiceFace {
    HELMET,
    ARROW,
    SHIELD,
    AXE,
    HAND,
    AXE2,
}

data class DiceSide(
    val idDice:Int,
    val name: Int,
    val description: Int,
    val img: Int,
    val side: DiceFace,
    var favor: Boolean,
)

data class Dice(
    val sides: List<DiceSide>,
    val tokenSides: List<DiceFace>,
)

val diceFacesDefenses = setOf(DiceFace.HELMET,DiceFace.ARROW)
val diceFacesAttacks = setOf(DiceFace.ARROW,DiceFace.AXE,DiceFace.AXE2)

val diceSides = listOf<DiceSide>(
    DiceSide(
        name = R.string.shield,
        side = DiceFace.SHIELD,
        img = R.drawable.shield,
        favor = false,
        idDice=0,
        description = R.string.desc_shield,
    ),
    DiceSide(
        name = R.string.hand,
        side = DiceFace.HAND,
        img = R.drawable.hand,
        favor = false,
        idDice=0,
        description = R.string.desc_hand,
    ),
    DiceSide(
        name = R.string.arrow,
        side = DiceFace.ARROW,
        img = R.drawable.arrow,
        favor = false,
        idDice=0,
        description = R.string.desc_arrow,
    ),
    DiceSide(
        name = R.string.axe,
        side = DiceFace.AXE,
        img = R.drawable.axe,
        favor = false,
        idDice=0,
        description = R.string.desc_axe,
    ),
    DiceSide(
        name = R.string.helmet,
        side = DiceFace.HELMET,
        img = R.drawable.helmet,
        favor = false,
        idDice=0,
        description = R.string.desc_helmet,
    ),
    DiceSide(
        name = R.string.axe,
        side = DiceFace.AXE2,
        img = R.drawable.axe,
        favor = false,
        idDice=0,
        description = R.string.desc_axe,
    ),
)

val dices = mutableListOf<Dice>(
    Dice(
        sides = diceSides,
        tokenSides = listOf(
            DiceFace.ARROW,
            DiceFace.HELMET,
        ),
    ),
    Dice(
        sides = diceSides,
        tokenSides = listOf(
            DiceFace.SHIELD,
            DiceFace.HELMET,
        ),
    ),
    Dice(
        sides = diceSides,
        tokenSides = listOf(
            DiceFace.HAND,
            DiceFace.HELMET,
        ),
    ),
    Dice(
        sides = diceSides,
        tokenSides = listOf(
            DiceFace.SHIELD,
            DiceFace.ARROW,
        ),
    ),
    Dice(
        sides = diceSides,
        tokenSides = listOf(
            DiceFace.SHIELD,
            DiceFace.HAND,
        ),
    ),
    Dice(
        sides = diceSides,
        tokenSides = listOf(
            DiceFace.HAND,
            DiceFace.ARROW,
        ),
    ),
)