package com.iago.orlog.screens.match.commons

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iago.orlog.R
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.navigation.Screens
import com.iago.orlog.utils.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EndGameDialog(
    navController: NavHostController,
    winner: MutableState<Player?>,
    viewModel: ViewModelOrlog
) {
    val coroutineScope = rememberCoroutineScope()
    var dialogPhaseVisible = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = winner.value) {
        if (winner.value != null)
            dialogPhaseVisible.value = true
    }

    LaunchedEffect(key1 = dialogPhaseVisible.value) {
        if (dialogPhaseVisible.value) {
          showPhase(coroutineScope,dialogPhaseVisible,navController, viewModel,winner)
        }
    }

    val alphaOpacity: Float by animateFloatAsState(
        targetValue = if (dialogPhaseVisible.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1500,
            easing = LinearEasing,
        ),
    )

    val alphaOpacityBackground: Float by animateFloatAsState(
        targetValue = if (dialogPhaseVisible.value) .85f else 0f,
        animationSpec = tween(
            durationMillis = 1500,
            easing = LinearEasing,
        ),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(alphaOpacityBackground)
            .background(MaterialTheme.colors.background)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(alphaOpacity),
        contentAlignment = Alignment.Center
    ) {

        Text(
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(horizontal = 10.dp),
            text = stringResource(if (winner.value!!.ia) R.string.lost else R.string.won),
        )
    }
}

fun showPhase(coroutineScope: CoroutineScope, dialogPhaseVisible: MutableState<Boolean>, navController: NavHostController, viewModel: ViewModelOrlog, winner: MutableState<Player?>) {
    coroutineScope.launch {
        dialogPhaseVisible.value = true
        delay(3000L)
        dialogPhaseVisible.value = false
        navController.navigate(Screens.HomeScreen.name)
        winner.value = null
        viewModel.resetGame()
    }
}