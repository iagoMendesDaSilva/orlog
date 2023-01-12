package com.iago.orlog

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.iago.orlog.utils.COIN
import com.iago.orlog.utils.God
import com.iago.orlog.utils.MODES
import com.iago.orlog.utils.Player
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
        coinFace = COIN.FACE_UP,
    )

    private val initialPlayer2 = Player(
        hp = 15,
        name = R.string.player2,
        gods = mutableListOf(),
        coinFace = COIN.FACE_DOWN,
    )

    var turn = mutableStateOf(COIN.FACE_UP)
    var mode = mutableStateOf(MODES.ONE_PLAYER)
    var player1 = mutableStateOf(initialPlayer1)
    var player2 = mutableStateOf(initialPlayer2)


    fun updateCurrentPlayer(property: String, value: Any) {
        val currentPlayer = getCurrentPlayer()
        val newPlayer = currentPlayer.value.copy(
            hp = (if (property === "hp") value else currentPlayer.value.hp) as Int,
            name = (if (property === "name") value else currentPlayer.value.name) as Int,
            gods = (if (property === "gods") value else currentPlayer.value.gods) as MutableList<God>,
            coinFace = (if (property === "coinFace") value else currentPlayer.value.coinFace) as COIN,
        )
        currentPlayer.value = newPlayer
    }

    fun updateMode(newMode: MODES) {
        mode.value = newMode
    }

    fun changeTurn() {
        turn.value = if (turn.value == COIN.FACE_UP) COIN.FACE_DOWN else COIN.FACE_UP
    }

    fun getCurrentPlayer(): MutableState<Player> {
        return if (turn.value == COIN.FACE_UP) player1 else player2
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

        viewModel.updateCurrentPlayer("gods", list)
    }

    fun makeDecisionDelayed(delay: Long, listIterations: List<() -> Unit>, coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            listIterations.forEach {
                it()
                delay(delay)
            }
        }
    }


}

