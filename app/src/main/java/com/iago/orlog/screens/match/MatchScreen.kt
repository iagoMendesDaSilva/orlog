package com.iago.orlog.screens.match

import android.annotation.SuppressLint
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
import kotlinx.coroutines.delay
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.functions

@SuppressLint("MutableCollectionMutableState")
@Composable
fun MatchScreen(navController: NavHostController, viewModel: ViewModelOrlog) {

    val context = LocalContext.current
    val currentRotation = remember { mutableStateOf(0f) }
    val rotation = remember { Animatable(currentRotation.value) }
    val delayingNextPhase = remember { mutableStateOf(false) }
    val winner = remember { mutableStateOf<Player?>(null) }

    val dialogPhaseShowing = remember { mutableStateOf(true) }
    val dicesTablePlayer1 = remember { mutableStateOf(getRandomDiceSides()) }
    val dicesTablePlayer2 = remember { mutableStateOf(getRandomDiceSides()) }
    val dicesSelectedPlayer1 = remember { mutableStateOf<List<DiceSide>>(emptyList()) }
    val dicesSelectedPlayer2 = remember { mutableStateOf<List<DiceSide>>(emptyList()) }

    val enablePress = !dialogPhaseShowing.value && !rotation.isRunning && !delayingNextPhase.value

    LaunchedEffect(key1 = viewModel.phase.value, block = {
        if (viewModel.round.value != 1 && viewModel.phase.value == Phase.ROLL_PHASE) {
            delayingNextPhase.value = true
            delay(2000L)
            delayingNextPhase.value = false
        }
        dialogPhaseShowing.value = true
    })

    LaunchedEffect(key1 = dialogPhaseShowing.value, block = {
        if (enablePress && viewModel.phase.value === Phase.RESOLUTION_PHASE)
            verifyResolutionPhase(
                context,
                viewModel,
                dicesTablePlayer1,
                dicesTablePlayer2,
                dicesSelectedPlayer1,
                dicesSelectedPlayer2,
                winner,
            )
    })

    verifyPhase(dicesTablePlayer1, dicesTablePlayer2, viewModel)

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
            enablePress,
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
            enablePress,
        )
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Coin(rotation, viewModel.turn.value, 100.dp)
    }

    if (winner.value != null)
        EndGameDialog(navController, winner, viewModel)

    PhaseDialog(dialogPhaseShowing, viewModel.phase.value)
}


fun verifyPhase(
    dicesTablePlayer1: MutableState<MutableList<DiceSide>>,
    dicesTablePlayer2: MutableState<MutableList<DiceSide>>,
    viewModel: ViewModelOrlog,
) {
    viewModel.phase.value =
        if (dicesTablePlayer1.value.isEmpty() && dicesTablePlayer2.value.isEmpty())
            if (viewModel.round.value == 1 || viewModel.player1.value.favorResolution != null && viewModel.player2.value.favorResolution != null) Phase.RESOLUTION_PHASE
            else Phase.GOD_FAVOR_PHASE
        else
            Phase.ROLL_PHASE
}

fun verifyResolutionPhase(
    context: Context,
    viewModel: ViewModelOrlog,
    dicesTablePlayer1: MutableState<MutableList<DiceSide>>,
    dicesTablePlayer2: MutableState<MutableList<DiceSide>>,
    dicesSelectedPlayer1: MutableState<List<DiceSide>>,
    dicesSelectedPlayer2: MutableState<List<DiceSide>>,
    winner: MutableState<Player?>
) {

    val player1 = viewModel.player1
    val player2 = viewModel.player2

    val player1Favor = verifyFavorResolution(player1.value)
    val player2Favor = verifyFavorResolution(player1.value)

    val godFavorsMethods = mutableListOf<FavorConfig>()

    if (player1Favor != null)
        godFavorsMethods.add(
            FavorConfig(
                player1Favor.useBeforeResolution
            ) {
                useGodFavor(
                    context,
                    player1.value.favorResolution,
                    viewModel,
                    player1,
                    player2,
                    dicesSelectedPlayer1,
                    dicesSelectedPlayer2
                )
            }
        )

    if (player2Favor != null)
        godFavorsMethods.add(
            FavorConfig(
                player2Favor.useBeforeResolution
            ) {
                useGodFavor(
                    context,
                    player2.value.favorResolution!!,
                    viewModel,
                    player2,
                    player1,
                    dicesSelectedPlayer2,
                    dicesSelectedPlayer1
                )
            }
        )

    if (player1Favor?.priority ?: 0 < player2Favor?.priority ?: 0)
        godFavorsMethods.reverse()

    godFavorsMethods.forEach {
        if (it.useBeforeResolution)
            it.method()
    }

    resolution(viewModel, dicesSelectedPlayer1, dicesSelectedPlayer2)

    godFavorsMethods.forEach {
        if (!it.useBeforeResolution)
            it.method()
    }

    verifyEndGame(
        viewModel.player1,
        viewModel.player2,
        viewModel,
        dicesTablePlayer1,
        dicesTablePlayer2,
        dicesSelectedPlayer1,
        dicesSelectedPlayer2,
        winner,
    )

}

fun verifyFavorResolution(player: Player): God? {
    return if (player.favorResolution?.favor != null && player.favorResolution.godId != null)
        gods.firstOrNull { it.id == player.favorResolution.godId }
    else null
}

fun verifyEndGame(
    player1: MutableState<Player>,
    player2: MutableState<Player>,
    viewModel: ViewModelOrlog,
    dicesTablePlayer1: MutableState<MutableList<DiceSide>>,
    dicesTablePlayer2: MutableState<MutableList<DiceSide>>,
    dicesSelectedPlayer1: MutableState<List<DiceSide>>,
    dicesSelectedPlayer2: MutableState<List<DiceSide>>,
    winner: MutableState<Player?>
) {
    viewModel.round.value = viewModel.round.value + 1

    if (player1.value.gems <= 0 || player2.value.gems <= 0)
        winner.value = if (player1.value.gems > 0) player1.value else player2.value
    else {
        restartRollPhase(
            viewModel,
            player1,
            player2,
            dicesTablePlayer1,
            dicesTablePlayer2,
            dicesSelectedPlayer1,
            dicesSelectedPlayer2
        )
    }
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

fun restartRollPhase(
    viewModel: ViewModelOrlog,
    player1: MutableState<Player>,
    player2: MutableState<Player>,
    dicesTablePlayer1: MutableState<MutableList<DiceSide>>,
    dicesTablePlayer2: MutableState<MutableList<DiceSide>>,
    dicesSelectedPlayer1: MutableState<List<DiceSide>>,
    dicesSelectedPlayer2: MutableState<List<DiceSide>>
) {
    viewModel.updatePlayer("reroll", 3, player1)
    viewModel.updatePlayer("reroll", 3, player2)
    viewModel.updatePlayer("favorResolution", null, player1)
    viewModel.updatePlayer("favorResolution", null, player2)
    dicesSelectedPlayer1.value = emptyList()
    dicesSelectedPlayer2.value = emptyList()
    dicesTablePlayer1.value = getRandomDiceSides()
    dicesTablePlayer2.value = getRandomDiceSides()
    viewModel.phase.value = Phase.ROLL_PHASE
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
        GodFavors.verifyAttack(opponentDefensesToUse, defenseDiceSide) { damage, defensesUpdated ->
            if (damage)
                playerDamage += 1
            opponentDefensesToUse = defensesUpdated
        }
    }
    return playerDamage
}

fun getRandomDiceSides(diceSides: MutableList<DiceSide>? = null): MutableList<DiceSide> {
    val dices = dices.mapIndexed { index, dice ->
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
    favor: FavorResolution?,
    viewModel: ViewModelOrlog,
    player: MutableState<Player>,
    opponent: MutableState<Player>,
    dicesSelectedPlayer: MutableState<List<DiceSide>>,
    dicesSelectedOpponent: MutableState<List<DiceSide>>
) {
    if (favor?.favor != null && favor.godId != null) {
        val godFavorID = context.getString(favor.godId)
        val companionObject = GodFavors::class.companionObject

        if (companionObject != null) {
            val companionInstance = GodFavors::class.companionObjectInstance
            val functionEx = companionObject.functions.first { it.name == "use${godFavorID}Favor" }

            functionEx.call(
                companionInstance,
                viewModel,
                player,
                opponent,
                favor.favor,
                dicesSelectedPlayer,
                dicesSelectedOpponent
            )
            val finalTokens = (player.value.tokens - favor.favor.cost).coerceAtLeast(0)
            viewModel.updatePlayer("tokens", finalTokens, player)
        }
    }
}