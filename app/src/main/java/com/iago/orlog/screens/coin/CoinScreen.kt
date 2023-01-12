package com.iago.orlog.screens.coin

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.iago.orlog.R
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.screens.coin.commons.ButtonCoin
import com.iago.orlog.screens.coin.commons.Coin
import com.iago.orlog.utils.COIN
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun CoinScreen(navController: NavHostController) {

    val coroutineScope = rememberCoroutineScope()
    var currentRotation = remember { mutableStateOf(0f) }
    var decision = remember { mutableStateOf<COIN>(COIN.UNDEFINED) }
    var coinResult = remember { mutableStateOf<COIN>(COIN.UNDEFINED) }

    val rotation = remember { Animatable(currentRotation.value) }

    val viewModel = hiltViewModel<ViewModelOrlog>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Header()
        Coin(rotation, coinResult.value) {
            if (decision.value != COIN.UNDEFINED && coinResult.value == COIN.UNDEFINED)
                animation(rotation, currentRotation, coinResult, coroutineScope) {
                   decideTurns(decision.value, coinResult.value)
                }
        }
        ButtonsHeadNTail(decision)
    }
}

@Composable
fun Header() {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Text(
            text = stringResource(R.string.coin),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.primary,
        )
        Text(
            text = stringResource(R.string.coin_desc),
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.secondary,
        )
    }
}

@Composable
fun ButtonsHeadNTail(decision: MutableState<COIN>) {
    Row() {
        ButtonCoin(
            text = R.string.head,
            active = decision.value == COIN.FACE_UP
        ) {
            decision.value = COIN.FACE_UP
        }
        ButtonCoin(
            text = R.string.tail,
            active = decision.value == COIN.FACE_DOWN
        ) {
            decision.value = COIN.FACE_DOWN
        }
    }
}

fun decideTurns(decision: COIN, coinResult: COIN) {
    val player1Turn = if(decision == coinResult) decision else getOppositeCoin(decision)
    val player2Turn = getOppositeCoin(player1Turn)
}

fun getOppositeCoin(coin: COIN): COIN {
    return if (coin == COIN.FACE_UP) COIN.FACE_DOWN else COIN.FACE_UP
}

fun animation(
    rotation: Animatable<Float, AnimationVector1D>,
    currentRotation: MutableState<Float>,
    coinResult: MutableState<COIN>,
    coroutineScope: CoroutineScope,
    callBack: () -> Unit,
) {
    coroutineScope.launch {

        coinResult.value = COIN.FACE_UP
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
                if (coinResult.value == COIN.FACE_UP) COIN.FACE_DOWN else COIN.FACE_UP
        }
        callBack()
    }
}
