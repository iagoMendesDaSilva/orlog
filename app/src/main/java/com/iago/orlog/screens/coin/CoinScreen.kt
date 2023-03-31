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
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.iago.orlog.R
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.navigation.Screens
import com.iago.orlog.screens.coin.commons.*
import com.iago.orlog.utils.CoinSide
import com.iago.orlog.utils.Coins
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CoinScreen(navController: NavHostController, viewModel: ViewModelOrlog) {

    val coroutineScope = rememberCoroutineScope()

    var played = remember { mutableStateOf(false) }
    var decision = remember { mutableStateOf<CoinSide>(CoinSide.UNDEFINED) }

    var currentRotation = remember { mutableStateOf(0f) }
    val rotation = remember { Animatable(currentRotation.value) }
    var coinResult = remember { mutableStateOf<CoinSide>(CoinSide.UNDEFINED) }

    LaunchedEffect(key1 = decision.value) {
        if (decision.value != CoinSide.UNDEFINED && coinResult.value == CoinSide.UNDEFINED)
            animation(rotation, currentRotation, coinResult, coroutineScope) {
                coroutineScope.launch {
                    delay(600L)
                    decideTurns(decision.value, coinResult.value, viewModel, played)
                }
            }
    }

    if (played.value)
        DialogResult(navController, decision.value, coinResult.value)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Header()
        Coin(rotation, coinResult.value, 125.dp)
        ButtonsHeadNTail(decision)

    }
}

fun decideTurns(
    decision: CoinSide,
    coinResult: CoinSide,
    viewModel: ViewModelOrlog,
    played: MutableState<Boolean>
) {
    val player1Turn = if (decision === coinResult) decision else getOppositeCoin(decision)
    val player2Turn = getOppositeCoin(player1Turn)

    viewModel.updateTurn(coinResult)
    viewModel.updatePlayer("coinFace", player1Turn, viewModel.player1)
    viewModel.updatePlayer("coinFace", player2Turn, viewModel.player2)
    viewModel.updatePlayer(
        "ia",
        true,
        if (decision != coinResult) viewModel.player1 else viewModel.player2
    )

    played.value = true
}

fun getOppositeCoin(coin: CoinSide): CoinSide {
    return if (coin == CoinSide.FACE_UP) CoinSide.FACE_DOWN else CoinSide.FACE_UP
}

fun animation(
    rotation: Animatable<Float, AnimationVector1D>,
    currentRotation: MutableState<Float>,
    coinResult: MutableState<CoinSide>,
    coroutineScope: CoroutineScope,
    callBack: () -> Unit,
) {
    coroutineScope.launch {

        coinResult.value = CoinSide.FACE_UP
        val numberRotations = (5..10).random()

        rotation.animateTo(
            targetValue = currentRotation.value + 180f,
            animationSpec = repeatable(
                iterations = numberRotations,
                animation = tween(350, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        ) {
            currentRotation.value = value
            coinResult.value =
                if (coinResult.value == CoinSide.FACE_UP) CoinSide.FACE_DOWN else CoinSide.FACE_UP
        }
        callBack()
    }
}
