package com.iago.orlog.screens.match.commons

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.screens.coin.commons.Coin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MatchDivision(viewModel: ViewModelOrlog) {

    val coroutineScope = rememberCoroutineScope()
    var currentRotation = remember { mutableStateOf(0f) }
    val rotation = remember { Animatable(currentRotation.value) }
    val firstLoad = remember { mutableStateOf(true) }

    LaunchedEffect(key1 = viewModel.turn.value) {
        if (!firstLoad.value)
            animation(rotation, currentRotation, coroutineScope)
        firstLoad.value = false
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
                .weight(1f)
                .background(MaterialTheme.colors.secondary)
        ) {}
        Coin(rotation, viewModel.turn.value, viewModel) {}
        Row(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
                .weight(1f)
                .background(MaterialTheme.colors.secondary)
        ) {}
    }
}

fun animation(
    rotation: Animatable<Float, AnimationVector1D>,
    currentRotation: MutableState<Float>,
    coroutineScope: CoroutineScope,
) {
    coroutineScope.launch {

        rotation.animateTo(
            targetValue = currentRotation.value + 180f,
            animationSpec = repeatable(
                iterations = 1,
                animation = tween(350, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        ) {
            currentRotation.value = value
        }
    }
}