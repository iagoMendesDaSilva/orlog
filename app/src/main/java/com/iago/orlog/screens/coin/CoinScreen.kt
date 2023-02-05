package com.iago.orlog.screens.coin

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iago.orlog.R
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.navigation.Screens
import com.iago.orlog.screens.coin.commons.ButtonCoin
import com.iago.orlog.screens.coin.commons.Coin
import com.iago.orlog.utils.Coin
import com.iago.orlog.utils.MODES
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun CoinScreen(navController: NavHostController, viewModel: ViewModelOrlog) {

    val coroutineScope = rememberCoroutineScope()
    var played = remember { mutableStateOf(false) }
    var currentRotation = remember { mutableStateOf(0f) }
    var decision = remember { mutableStateOf<Coin>(Coin.UNDEFINED) }
    var coinResult = remember { mutableStateOf<Coin>(Coin.UNDEFINED) }

    val rotation = remember { Animatable(currentRotation.value) }

    LaunchedEffect(key1 = decision.value) {
        if (decision.value != Coin.UNDEFINED && coinResult.value == Coin.UNDEFINED)
            animation(rotation, currentRotation, coinResult, coroutineScope) {
                decideTurns(decision.value, coinResult.value, viewModel, played)
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Header(decision.value, coinResult.value, played.value)
        Coin(rotation, coinResult.value, viewModel, 125.dp) {}
        if (played.value)
            ButtonChooseGodFavors(navController)
        else
            ButtonsHeadNTail(decision)
    }
}

@Composable
fun ButtonChooseGodFavors(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth(.9f)
            .padding(horizontal = 10.dp)
            .background(
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colors.onBackground,
            )
            .border(
                width = 1.dp,
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colors.secondary,
            )
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .clickable { navController.navigate(Screens.GodsScreen.name) },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.secondary,
            text = stringResource(R.string.select_gods_favors),
        )
    }
}

@Composable
fun Header(decision: Coin, coinResult: Coin, played: Boolean) {

    val winOrLose = if (decision === coinResult) R.string.won else R.string.lost
    val player = if (decision === coinResult) R.string.you_are_player1 else R.string.you_are_player2


    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Text(
            text = stringResource(if (played) winOrLose else R.string.coin),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.primary,
        )
        Text(
            text = stringResource(if (played) player else R.string.coin_desc),
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.secondary,
        )
    }
}

@Composable
fun ButtonsHeadNTail(decision: MutableState<Coin>) {
    Row() {
        ButtonCoin(
            text = R.string.head,
            active = decision.value == Coin.FACE_UP,
            enable = decision.value == Coin.UNDEFINED
        ) {
            decision.value = Coin.FACE_UP
        }
        ButtonCoin(
            text = R.string.tail,
            enable = decision.value == Coin.UNDEFINED,
            active = decision.value == Coin.FACE_DOWN
        ) {
            decision.value = Coin.FACE_DOWN
        }
    }
}

fun decideTurns(
    decision: Coin,
    coinResult: Coin,
    viewModel: ViewModelOrlog,
    played: MutableState<Boolean>
) {
    val player1Turn = if (decision === coinResult) decision else getOppositeCoin(decision)
    val player2Turn = getOppositeCoin(player1Turn)

    viewModel.updateTurn(coinResult)
    viewModel.updatePlayer("coinFace", player1Turn, viewModel.player1)
    viewModel.updatePlayer("coinFace", player2Turn, viewModel.player2)

    if (viewModel.mode.value === MODES.ONE_PLAYER)
        viewModel.iaTurn.value = getOppositeCoin(decision)

    played.value = true
}

fun getOppositeCoin(coin: Coin): Coin {
    return if (coin == Coin.FACE_UP) Coin.FACE_DOWN else Coin.FACE_UP
}

fun animation(
    rotation: Animatable<Float, AnimationVector1D>,
    currentRotation: MutableState<Float>,
    coinResult: MutableState<Coin>,
    coroutineScope: CoroutineScope,
    callBack: () -> Unit,
) {
    coroutineScope.launch {

        coinResult.value = Coin.FACE_UP
        val random = (5..10).random()

        rotation.animateTo(
            targetValue = currentRotation.value + 180f,
            animationSpec = repeatable(
                iterations = random,
                animation = tween(350, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        ) {
            currentRotation.value = value
            coinResult.value =
                if (coinResult.value == Coin.FACE_UP) Coin.FACE_DOWN else Coin.FACE_UP
        }
        callBack()
    }
}
