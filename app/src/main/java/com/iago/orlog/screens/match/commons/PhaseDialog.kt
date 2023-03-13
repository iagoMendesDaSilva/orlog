package com.iago.orlog.screens.match.commons

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.iago.orlog.R
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.utils.Phase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PhaseDialog(
    dialogPhaseShowing: MutableState<Boolean>,
    viewModel: ViewModelOrlog
) {

    val coroutineScope = rememberCoroutineScope()
    var dialogPhaseVisible = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = dialogPhaseShowing.value) {
        if(dialogPhaseShowing.value)
            dialogPhaseVisible.value = true
    }

    LaunchedEffect(key1 = dialogPhaseVisible.value){
        if(dialogPhaseVisible.value)
            showPhase(
                coroutineScope,
                dialogPhaseVisible,
                dialogPhaseShowing,
            )
    }

    val alphaOpacity: Float by animateFloatAsState(
        targetValue = if (dialogPhaseVisible.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1500,
            easing = LinearEasing,
        ),
    )

    val alphaOpacityBackground: Float by animateFloatAsState(
        targetValue = if (dialogPhaseVisible.value) .5f else 0f,
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

        Row(
            Modifier
                .background(
                    color = MaterialTheme.colors.onBackground,
                    shape = MaterialTheme.shapes.medium
                )
                .zIndex(5f)
                .padding(vertical = 25.dp, horizontal = 50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body2,
                text = stringResource(getPhaseName(viewModel)),
            )
        }

    }
}

fun getPhaseName(viewModel: ViewModelOrlog): Int {
    return when (viewModel.phase.value) {
        Phase.GOD_FAVOR_PHASE -> R.string.god_favor_phase
        Phase.RESOLUTION_PHASE -> R.string.resolution_phase
        else -> R.string.roll_phase
    }
}

fun showPhase(
    coroutineScope: CoroutineScope,
    dialogPhaseVisible: MutableState<Boolean>,
    dialogPhaseShowing: MutableState<Boolean>,
) {
    coroutineScope.launch {
        dialogPhaseVisible.value = true //start animation
        delay(2500L) //show and stay
        dialogPhaseVisible.value = false //close animation
        delay(1500L)// time close
        dialogPhaseShowing.value = false// close modal
    }
}