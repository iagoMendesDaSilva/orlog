package com.iago.orlog.screens.coin

import android.util.Log
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
import com.iago.orlog.screens.coin.commons.ButtonCoin
import com.iago.orlog.screens.coin.commons.Coin
import com.iago.orlog.utils.Coin
import com.iago.orlog.utils.Coins
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun CoinScreen(navController: NavHostController, viewModel: ViewModelOrlog) {

    val coroutineScope = rememberCoroutineScope()

    var played = remember { mutableStateOf(false) }
    var decision = remember { mutableStateOf<Coin>(Coin.UNDEFINED) }

    var currentRotation = remember { mutableStateOf(0f) }
    val rotation = remember { Animatable(currentRotation.value) }
    var coinResult = remember { mutableStateOf<Coin>(Coin.UNDEFINED) }

    LaunchedEffect(key1 = decision.value) {
        if (decision.value != Coin.UNDEFINED && coinResult.value == Coin.UNDEFINED)
            animation(rotation, currentRotation, coinResult, coroutineScope) {
                decideTurns(decision.value, coinResult.value, viewModel, played)
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

@Composable
fun DialogResult(
    navController: NavHostController,
    decision: Coin,
    coinResult: Coin
) {
    val winOrLose = if (decision == coinResult) R.string.won else R.string.lost
    val player = if (decision == coinResult) R.string.you_are_player1 else R.string.you_are_player2

    Dialog(
        onDismissRequest = {},
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colors.onBackground,
                        shape = MaterialTheme.shapes.medium,
                    )
                    .padding(vertical = 40.dp, horizontal = 25.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Text(
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.secondary,
                    text = stringResource(winOrLose),
                )
                Text(
                    style = MaterialTheme.typography.subtitle2,
                    color = MaterialTheme.colors.primary,
                    text = stringResource(player),
                )
                Spacer(modifier = Modifier.height(25.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .background(
                            shape = MaterialTheme.shapes.large,
                            color = MaterialTheme.colors.onBackground,
                        )
                        .border(
                            width = 1.dp,
                            shape = MaterialTheme.shapes.large,
                            color = MaterialTheme.colors.secondary,
                        )
                        .padding(vertical = 12.dp, horizontal = 20.dp)
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
        },
    )
}

@Composable
fun Header() {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Text(
            text = stringResource( R.string.coin),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.primary,
        )
        Text(
            text = stringResource( R.string.coin_desc),
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.secondary,
        )
    }
}

@Composable
fun ButtonsHeadNTail(decision: MutableState<Coin>) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        ButtonCoin(
            coin = Coins.head,
            active = decision.value == Coin.FACE_UP,
            enable = decision.value == Coin.UNDEFINED
        ) {
            decision.value = Coin.FACE_UP
        }
        ButtonCoin(
            coin = Coins.tail,
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
    viewModel.updatePlayer(
        "ia",
        true,
        if (decision != coinResult) viewModel.player1 else viewModel.player2
    )

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
                if (coinResult.value == Coin.FACE_UP) Coin.FACE_DOWN else Coin.FACE_UP
        }
        callBack()
    }
}
