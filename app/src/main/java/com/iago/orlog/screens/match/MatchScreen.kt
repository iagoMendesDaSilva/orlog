package com.iago.orlog.screens.match

import android.content.Context
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.screens.coin.commons.Coin
import com.iago.orlog.screens.match.commons.*
import com.iago.orlog.utils.*

@Composable
fun MatchScreen(navController: NavHostController, viewModel: ViewModelOrlog) {

    var currentRotation = remember { mutableStateOf(0f) }
    val rotation = remember { Animatable(currentRotation.value) }

    var dialogPhaseShowing = remember { mutableStateOf(true) }
    val dicesTablePlayer1 = remember { mutableStateOf(getRandomDiceSides()) }
    val dicesTablePlayer2 = remember { mutableStateOf(getRandomDiceSides()) }
    val dicesSelectedPlayer1 = remember { mutableStateOf<List<DiceSide>>(emptyList()) }
    val dicesSelectedPlayer2 = remember { mutableStateOf<List<DiceSide>>(emptyList()) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Coin(rotation, viewModel.turn.value, viewModel, 100.dp) {}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PlayerTable(
            Modifier
                .fillMaxHeight()
                .weight(1f)
                .rotate(180f),
            viewModel,
            if (viewModel.player1.value.ia) viewModel.player1 else viewModel.player2,
            dicesSelectedPlayer2,
            dicesTablePlayer2,
            dialogPhaseShowing.value,
        )
        MatchDivision(viewModel, rotation, currentRotation)
        PlayerTable(
            Modifier
                .fillMaxHeight()
                .weight(1f),
            viewModel,
            if (viewModel.player1.value.ia) viewModel.player2 else viewModel.player1,
            dicesSelectedPlayer1,
            dicesTablePlayer1,
            dialogPhaseShowing.value
        )
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Coin(rotation, viewModel.turn.value, viewModel, 100.dp) {}
    }

    PhaseDialog(dialogPhaseShowing, viewModel)

}

fun verifyGodFavorPhase(
    viewModel: ViewModelOrlog,
    dicesSelectedPlayer1: MutableState<List<DiceSide>>,
    dicesSelectedPlayer2: MutableState<List<DiceSide>>
) {
    Log.d("TAG", viewModel.phase.value.toString())
}

fun verifyRollPhase(
    dicesSelectedPlayer1: MutableState<List<DiceSide>>,
    dicesSelectedPlayer2: MutableState<List<DiceSide>>,
    dicesTablePlayer1: MutableState<MutableList<DiceSide>>,
    dicesTablePlayer2: MutableState<MutableList<DiceSide>>
) {
    dicesSelectedPlayer1.value = emptyList()
    dicesSelectedPlayer2.value = emptyList()
    dicesTablePlayer1.value = getRandomDiceSides()
    dicesTablePlayer2.value = getRandomDiceSides()
}

fun verifyPhase(
    dicesTablePlayer1: MutableState<MutableList<DiceSide>>,
    dicesTablePlayer2: MutableState<MutableList<DiceSide>>,
    viewModel: ViewModelOrlog,
) {
    viewModel.phase.value =
        if (dicesTablePlayer1.value.isEmpty() && dicesTablePlayer2.value.isEmpty())
            if (viewModel.round.value === 1) Phase.RESOLUTION_PHASE
            else Phase.GOD_FAVOR_PHASE
        else
            Phase.ROLL_PHASE

}

fun verifyResolutionPhase(
    context: Context,
    viewModel: ViewModelOrlog,
    dicesSelectedPlayer1: MutableState<List<DiceSide>>,
    dicesSelectedPlayer2: MutableState<List<DiceSide>>
) {

    val player1 = viewModel.player1
    val player2 = viewModel.player2


    if (player1.value.favorResolution != null && gods.first { it.id == player1.value.favorResolution!!.godId }.useBeforeResolution)
        useGodFavor(context, player1.value.favorResolution!!, viewModel, player1, player2)

    if (player2.value.favorResolution != null && gods.first { it.id == player2.value.favorResolution!!.godId }.useBeforeResolution)
        useGodFavor(context, player2.value.favorResolution!!, viewModel, player2, player1)

    resolution(viewModel, dicesSelectedPlayer1, dicesSelectedPlayer2)

    if (player1.value.favorResolution != null && !gods.first { it.id == player1.value.favorResolution!!.godId }.useBeforeResolution)
        useGodFavor(context, player1.value.favorResolution!!, viewModel, player1, player2)

    if (player2.value.favorResolution != null && !gods.first { it.id == player2.value.favorResolution!!.godId }.useBeforeResolution)
        useGodFavor(context, player2.value.favorResolution!!, viewModel, player2, player1)


    verifyEndGame(viewModel.player1, viewModel.player2, viewModel)

}

fun resolution(
    viewModel: ViewModelOrlog,
    dicesSelectedPlayer1: MutableState<List<DiceSide>>,
    dicesSelectedPlayer2: MutableState<List<DiceSide>>
) {
    getTokens(dicesSelectedPlayer1, viewModel.player1, viewModel.player2, viewModel)
    getTokens(dicesSelectedPlayer2, viewModel.player2, viewModel.player1, viewModel)
    attackOpponent(
        dicesSelectedPlayer1,
        dicesSelectedPlayer2,
        viewModel.player1,
        viewModel.player2,
        viewModel
    )
}

fun verifyEndGame(
    player1: MutableState<Player>,
    player2: MutableState<Player>,
    viewModel: ViewModelOrlog
) {
    viewModel.round.value = viewModel.round.value + 1

    if (player1.value.gems <= 0 || player2.value.gems <= 0)
        Log.d("TAG", "ENDGAME")
    else {
        restartRollPhase(viewModel, player1, player2)
    }
}

fun restartRollPhase(
    viewModel: ViewModelOrlog,
    player1: MutableState<Player>,
    player2: MutableState<Player>
) {
    viewModel.updatePlayer("reroll", 3, player1)
    viewModel.updatePlayer("reroll", 3, player2)
    viewModel.godFavorStatus.value = GodFavorStatus.NO_PLAYER
    viewModel.updatePlayer("favorResolution", null, player1)
    viewModel.updatePlayer("favorResolution", null, player2)
    viewModel.phase.value = Phase.ROLL_PHASE
}

fun attackOpponent(
    dicesSelectedPlayer1: MutableState<List<DiceSide>>,
    dicesSelectedPlayer2: MutableState<List<DiceSide>>,
    player1: MutableState<Player>,
    player2: MutableState<Player>,
    viewModel: ViewModelOrlog
) {
    val player1Attacks = dicesSelectedPlayer1.value.filter { it.side in diceFacesAttacks }
    val player2Attacks = dicesSelectedPlayer2.value.filter { it.side in diceFacesAttacks }
    val player1Defenses = dicesSelectedPlayer1.value.filter { it.side in diceFacesDefenses }
    val player2Defenses = dicesSelectedPlayer2.value.filter { it.side in diceFacesDefenses }

    val player1Damage = getFinalDamage(player1Attacks, player2Defenses)
    val player2Damage = getFinalDamage(player2Attacks, player1Defenses)

    viewModel.updatePlayer("gems", (player1.value.gems - player2Damage).coerceAtLeast(0), player1)
    viewModel.updatePlayer("gems", (player2.value.gems - player1Damage).coerceAtLeast(0), player2)
}

fun getFinalDamage(
    playerAttacks: List<DiceSide>,
    opponentDefenses: List<DiceSide>
): Int {
    var playerDamage = 0
    var opponentDefensesToUse = opponentDefenses.toMutableList()

    playerAttacks.forEach { diceSide ->
        val defenseDiceSide =
            if (diceSide.side === DiceFace.ARROW) DiceFace.SHIELD else DiceFace.HELMET
        verifyAttack(opponentDefensesToUse, defenseDiceSide) { damage, defensesUpdated ->
            if (damage)
                playerDamage += 1
            opponentDefensesToUse = defensesUpdated
        }
    }
    return playerDamage
}

fun verifyAttack(
    opponentDefenses: MutableList<DiceSide>,
    diceFace: DiceFace,
    onAttack: (damage: Boolean, opponentDefenses: MutableList<DiceSide>) -> Unit
) {
    var damage = false
    if (opponentDefenses.any { it.side == diceFace }) {
        val index = opponentDefenses.indexOfFirst { it.side === diceFace }
        opponentDefenses.removeAt(index)
    } else
        damage = true
    onAttack(damage, opponentDefenses)
}

fun getTokens(
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
    player: MutableState<Player>,
    opponent: MutableState<Player>,
    viewModel: ViewModelOrlog
) {
    val getTokens = dicesSelectedPlayer.value.count { it.favor }
    val stealTokens = dicesSelectedPlayer.value.count { it.side === DiceFace.HAND }
    val totalTokens = player.value.tokens + getTokens + stealTokens

    viewModel.updatePlayer("tokens", totalTokens, player)
    viewModel.updatePlayer(
        "tokens",
        (opponent.value.tokens - stealTokens).coerceAtLeast(0),
        opponent
    )
}


@Composable
fun PlayerTable(
    modifier: Modifier,
    viewModel: ViewModelOrlog,
    player: MutableState<Player>,
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
    dicesTablePlayer: MutableState<MutableList<DiceSide>>,
    dialogPhaseShowing: Boolean
) {


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
            RowDices(dicesSelectedPlayer, dicesTablePlayer, player, viewModel, dialogPhaseShowing) {
                endTurn(viewModel, player)
            }
            RowSelectedDices(dicesSelectedPlayer.value)
            FooterStatus(player, viewModel,dialogPhaseShowing,
                onPressEndTurn = { endTurn(viewModel, player) },
                pressGodFavor = { godFavor, favor ->
                    chooseGodFavor(
                        godFavor,
                        favor,
                        viewModel,
                        player,
                    )
                })
        }
    }
}

fun endTurn(viewModel: ViewModelOrlog, player: MutableState<Player>) {
    viewModel.changeTurn()
    if (viewModel.phase.value == Phase.ROLL_PHASE)
        viewModel.updatePlayer("reroll", player.value.reroll - 1, player)

    if (viewModel.phase.value == Phase.GOD_FAVOR_PHASE) {
        if (viewModel.godFavorStatus.value === GodFavorStatus.NO_PLAYER)
            viewModel.godFavorStatus.value = GodFavorStatus.ONE_PLAYER
        else viewModel.godFavorStatus.value = GodFavorStatus.TWO_PLAYERS
    }

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

fun chooseGodFavor(
    godFavor: God,
    favor: Favor,
    viewModel: ViewModelOrlog,
    player: MutableState<Player>
) {
    viewModel.updatePlayer("favorResolution", FavorResolution(favor, godFavor.id), player)
    endTurn(viewModel, player)
}

fun useGodFavor(
    context: Context,
    favor: FavorResolution,
    viewModel: ViewModelOrlog,
    player: MutableState<Player>,
    opponent: MutableState<Player>
) {
    Log.d("TAG", favor.favor.cost.toString())
//    val godFavorID = context.getString(favor.godId)
//    val companionObject = GodFavors::class.companionObject
//    if (companionObject != null) {
//        val companionInstance = GodFavors::class.companionObjectInstance
//        val functionEx = companionObject.functions.first { it.name == "use${godFavorID}Favor" }
//        functionEx.call(companionInstance, viewModel, player, opponent, favor)
//    }
}