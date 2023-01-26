package com.iago.orlog

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.iago.orlog.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ViewModelOrlog @Inject constructor() : ViewModel() {

    private val initialPlayer1 = Player(
        hp = 15,
        name = R.string.player1,
        gods = mutableListOf(),
        coinFace = COIN.UNDEFINED,
        tokens = 0,
        reroll = 3,
    )

    private val initialPlayer2 = Player(
        hp = 15,
        name = R.string.player2,
        gods = mutableListOf(),
        coinFace = COIN.UNDEFINED,
        tokens = 0,
        reroll = 3,
    )

    var iaTurn = mutableStateOf(COIN.UNDEFINED)
    var turn = mutableStateOf(COIN.UNDEFINED)
    var mode = mutableStateOf(MODES.ONE_PLAYER)
    var player1 = mutableStateOf(initialPlayer1)
    var player2 = mutableStateOf(initialPlayer2)


    fun updatePlayer(property: String, value: Any, player: MutableState<Player>? = null) {
        val playerToChange = player ?: getCurrentPlayer()

        val newPlayer = playerToChange.value.copy(
            hp = (if (property === "hp") value else playerToChange.value.hp) as Int,
            name = (if (property === "name") value else playerToChange.value.name) as Int,
            reroll = (if (property === "reroll") value else playerToChange.value.reroll) as Int,
            gods = (if (property === "gods") value else playerToChange.value.gods) as MutableList<God>,
            coinFace = (if (property === "coinFace") value else playerToChange.value.coinFace) as COIN,
        )
        playerToChange.value = newPlayer
    }

    fun getImageCoinByTurn(coin: COIN? = null): Int {
        val value = coin ?: turn.value
        return if (value == COIN.UNDEFINED || value == COIN.FACE_UP) R.drawable.coin_face else R.drawable.coin_no_face
    }

    fun updateMode(newMode: MODES) {
        mode.value = newMode
    }

    fun updateTurn(newTurn: COIN) {
        turn.value = newTurn
    }

    fun changeTurn() {
        turn.value = if (turn.value == COIN.FACE_UP) COIN.FACE_DOWN else COIN.FACE_UP
    }

    fun getCurrentPlayer(): MutableState<Player> {
        return if (turn.value === player1.value.coinFace) player1 else player2
    }

    fun updateGodsList(
        currentGods: MutableList<God>,
        add: Boolean,
        god: God,
        viewModel: ViewModelOrlog
    ) {
        var list = mutableListOf<God>()
        list.addAll(currentGods)
        if (add)
            list.add(god)
        else
            list.remove(god)

        viewModel.updatePlayer("gods", list)
    }


}

