package com.iago.orlog

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.iago.orlog.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ViewModelOrlog @Inject constructor() : ViewModel() {

    private val initialPlayer1 = Player(
        ia = false,
        favorResolution = null,
        gems = 15,
        name = R.string.player1,
        godFavors = mutableListOf(),
        coinFace = CoinSide.UNDEFINED,
        tokens = 0,
        reroll = 3,
    )

    private val initialPlayer2 = Player(
        ia = false,
        favorResolution = null,
        gems = 15,
        name = R.string.player2,
        godFavors = mutableListOf(),
        coinFace = CoinSide.UNDEFINED,
        tokens = 0,
        reroll = 3,
    )

    var round = mutableStateOf(1)
    var turn = mutableStateOf(CoinSide.UNDEFINED)
    var mode = mutableStateOf(MODES.ONE_PLAYER)
    var player1 = mutableStateOf(initialPlayer1)
    var player2 = mutableStateOf(initialPlayer2)
    var phase = mutableStateOf(Phase.ROLL_PHASE)


    fun updatePlayer(property: String, value: Any?, player: MutableState<Player>? = null) {
        val playerToChange = player ?: getCurrentPlayer()

        val newPlayer = playerToChange.value.copy(
            favorResolution = (if (property === "favorResolution") value else playerToChange.value.favorResolution) as FavorResolution?,
            gems = (if (property === "gems") value else playerToChange.value.gems) as Int,
            name = (if (property === "name") value else playerToChange.value.name) as Int,
            reroll = (if (property === "reroll") value else playerToChange.value.reroll) as Int,
            godFavors = (if (property === "godFavors") value else playerToChange.value.godFavors) as MutableList<God>,
            coinFace = (if (property === "coinFace") value else playerToChange.value.coinFace) as CoinSide,
            tokens = (if (property === "tokens") value else playerToChange.value.tokens) as Int,
            ia = (if (property === "ia") value else playerToChange.value.ia) as Boolean,
        )
        playerToChange.value = newPlayer
    }

    fun updateMode(newMode: MODES) {
        mode.value = newMode
    }

    fun updateTurn(newTurn: CoinSide) {
        turn.value = newTurn
    }

    fun changeTurn() {
        turn.value = if (turn.value == CoinSide.FACE_UP) CoinSide.FACE_DOWN else CoinSide.FACE_UP
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
        val list = mutableListOf<God>()
        list.addAll(currentGods)
        if (add)
            list.add(god)
        else
            list.remove(god)

        viewModel.updatePlayer("godFavors", list)
    }

    fun resetGame() {
        round.value = 1
        turn.value = CoinSide.UNDEFINED
        mode.value = MODES.ONE_PLAYER
        player1.value = initialPlayer1
        player2.value = initialPlayer2
        phase.value = Phase.ROLL_PHASE
    }


}

