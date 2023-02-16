package com.iago.orlog.screens.match

import android.content.Context
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.screens.coin.commons.Coin
import com.iago.orlog.screens.match.commons.*
import com.iago.orlog.utils.*
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.functions

@Composable
fun MatchScreen(navController: NavHostController, viewModel: ViewModelOrlog) {

    val rotate =
        viewModel.mode.value === MODES.ONE_PLAYER && viewModel.iaTurn.value == viewModel.player1.value.coinFace

    var dialogPhaseVisible = remember { mutableStateOf(false) }

    val dicesTablePlayer1 = remember { mutableStateOf(getRandomDiceSides()) }
    val dicesTablePlayer2 = remember { mutableStateOf(getRandomDiceSides()) }

    val dicesSelectedPlayer1 = remember { mutableStateOf<List<DiceSide>>(emptyList()) }
    val dicesSelectedPlayer2 = remember { mutableStateOf<List<DiceSide>>(emptyList()) }

    var currentRotation = remember { mutableStateOf(0f) }
    val rotation = remember { Animatable(currentRotation.value) }

    if (dicesTablePlayer1.value.isEmpty() && dicesTablePlayer2.value.isEmpty())
        dialogPhaseVisible.value = true

    LaunchedEffect(key1 = viewModel.phase.value) {
        verifyResolutionPhase(viewModel, dicesSelectedPlayer1, dicesSelectedPlayer2)
    }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Coin(rotation, viewModel.turn.value, viewModel, 100.dp) {}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
            .rotate(if (rotate) 180f else 0f),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PlayerTable(
            !rotate,
            Modifier
                .fillMaxHeight()
                .weight(1f)
                .rotate(180f), viewModel, viewModel.player2, dicesSelectedPlayer2, dicesTablePlayer2
        )
        MatchDivision(viewModel, rotate, rotation, currentRotation)
        PlayerTable(
            rotate,
            Modifier
                .fillMaxHeight()
                .weight(1f), viewModel, viewModel.player1, dicesSelectedPlayer1, dicesTablePlayer1
        )
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Coin(rotation, viewModel.turn.value, viewModel, 100.dp) {}
    }

    PhaseDialog(dialogPhaseVisible, dicesTablePlayer1, dicesTablePlayer2, viewModel)


}

fun verifyResolutionPhase(
    viewModel: ViewModelOrlog,
    dicesSelectedPlayer1: MutableState<List<DiceSide>>,
    dicesSelectedPlayer2: MutableState<List<DiceSide>>
) {
    if (viewModel.phase.value === Phase.RESOLUTION_PHASE) {
        getTokens(dicesSelectedPlayer1, viewModel.player1, viewModel)
        getTokens(dicesSelectedPlayer2, viewModel.player2, viewModel)
    }
}

fun getTokens(dicesSelectedPlayer: MutableState<List<DiceSide>>, player: MutableState<Player>, viewModel: ViewModelOrlog) {
    val tokensPlayer= dicesSelectedPlayer.value.count { it.favor }
    viewModel.updatePlayer("tokens", tokensPlayer, player)
}


@Composable
fun PlayerTable(
    rotate: Boolean,
    modifier: Modifier,
    viewModel: ViewModelOrlog,
    player: MutableState<Player>,
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
    dicesTablePlayer: MutableState<MutableList<DiceSide>>
) {

    val context = LocalContext.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        HeaderStatus(player)
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            RowDices(dicesSelectedPlayer, dicesTablePlayer, rotate, player, viewModel) {
                endTurn(viewModel, player)
            }
            RowSelectedDices(dicesSelectedPlayer.value)
            FooterStatus(player, rotate, viewModel,
                onPressEndTurn = { endTurn(viewModel, player) },
                pressGodFavor = { godFavor, favor ->
                    useGodFavor(
                        context,
                        godFavor,
                        favor,
                        viewModel,
                        player,
                        viewModel.getOpponent(player.value)
                    )
                })
        }
    }
}

fun endTurn(viewModel: ViewModelOrlog, player: MutableState<Player>) {
    viewModel.changeTurn()
    viewModel.updatePlayer("reroll", player.value.reroll - 1, player)
}

fun getRandomDiceSides(diceSides: MutableList<DiceSide>? = null): MutableList<DiceSide> {
    var dices = dices.mapIndexed { index, dice ->
        val item = dice.sides[(dice.sides.indices.random())]
        val favor = dice.tokenSides.contains(item.side)
        item.copy(favor = favor, idDice = index)
    }.toMutableList()

    if (diceSides != null) {
        return dices.filterIndexed { index, _ ->
            diceSides.any { it.idDice == index }
        }.toMutableList()
    }

    return dices
}

fun useGodFavor(
    context: Context,
    godFavor: God,
    favor: Favor,
    viewModel: ViewModelOrlog,
    player: MutableState<Player>,
    opponent: MutableState<Player>
) {
    val godFavorID = context.getString(godFavor.id)
    val companionObject = GodFavors::class.companionObject
    if (companionObject != null) {
        val companionInstance = GodFavors::class.companionObjectInstance
        val functionEx = companionObject.functions.first { it.name == "use${godFavorID}Favor" }
        functionEx.call(companionInstance, viewModel, player, opponent, favor)
    }
}