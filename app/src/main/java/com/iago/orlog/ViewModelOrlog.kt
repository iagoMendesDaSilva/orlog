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
        gems = 15,
        name = R.string.player1,
        godFavors = mutableListOf(),
        coinFace = Coin.UNDEFINED,
        tokens = 0,
        reroll = 3,
    )

    private val initialPlayer2 = Player(
        gems = 15,
        name = R.string.player2,
        godFavors = mutableListOf(),
        coinFace = Coin.UNDEFINED,
        tokens = 0,
        reroll = 3,
    )

    var round = mutableStateOf(1)
    var iaTurn = mutableStateOf(Coin.UNDEFINED)
    var turn = mutableStateOf(Coin.UNDEFINED)
    var mode = mutableStateOf(MODES.ONE_PLAYER)
    var player1 = mutableStateOf(initialPlayer1)
    var player2 = mutableStateOf(initialPlayer2)
    var phase = mutableStateOf(Phase.UNDEFINED)


    fun updatePlayer(property: String, value: Any, player: MutableState<Player>? = null) {
        val playerToChange = player ?: getCurrentPlayer()

        val newPlayer = playerToChange.value.copy(
            gems = (if (property === "gems") value else playerToChange.value.gems) as Int,
            name = (if (property === "name") value else playerToChange.value.name) as Int,
            reroll = (if (property === "reroll") value else playerToChange.value.reroll) as Int,
            godFavors = (if (property === "godFavors") value else playerToChange.value.godFavors) as MutableList<God>,
            coinFace = (if (property === "coinFace") value else playerToChange.value.coinFace) as Coin,
            tokens = (if (property === "tokens") value else playerToChange.value.tokens) as Int,
        )
        playerToChange.value = newPlayer
    }

    fun getImageCoinByTurn(coin: Coin? = null): Int {
        val value = coin ?: turn.value
        return if (value == Coin.UNDEFINED || value == Coin.FACE_UP) R.drawable.coin_face else R.drawable.coin_no_face
    }

    fun updateMode(newMode: MODES) {
        mode.value = newMode
    }

    fun updateTurn(newTurn: Coin) {
        turn.value = newTurn
    }

    fun changeTurn() {
        turn.value = if (turn.value == Coin.FACE_UP) Coin.FACE_DOWN else Coin.FACE_UP
    }

    fun getCurrentPlayer(): MutableState<Player> {
        return if (turn.value === player1.value.coinFace) player1 else player2
    }

    fun getOpponent(player: Player): MutableState<Player> {
        return if (player.coinFace === player1.value.coinFace) player2 else player1
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

        viewModel.updatePlayer("godFavors", list)
    }


}

