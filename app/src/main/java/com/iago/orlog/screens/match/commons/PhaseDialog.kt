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
import com.iago.orlog.utils.DiceSide
import com.iago.orlog.utils.Phase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PhaseDialog(
    dialogPhaseVisible: MutableState<Boolean>,
    dicesTablePlayer1: MutableState<MutableList<DiceSide>>,
    dicesTablePlayer2: MutableState<MutableList<DiceSide>>,
    viewModel: ViewModelOrlog
) {

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = dialogPhaseVisible.value) {
        showPhase(
            coroutineScope,
            dialogPhaseVisible,
            dicesTablePlayer1,
            dicesTablePlayer2,
            viewModel
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
                text = stringResource(
                    getPhaseName(
                        dicesTablePlayer1,
                        dicesTablePlayer2,
                        viewModel
                    )
                ),
            )
        }

    }
}

fun getPhaseName(
    dicesTablePlayer1: MutableState<MutableList<DiceSide>>,
    dicesTablePlayer2: MutableState<MutableList<DiceSide>>,
    viewModel: ViewModelOrlog
): Int {

    return if (dicesTablePlayer1.value.isEmpty() && dicesTablePlayer2.value.isEmpty())
        if (viewModel.round.value === 1) R.string.resolution_phase
        else R.string.god_favor_phase
    else
        R.string.roll_phase
}

fun showPhase(
    coroutineScope: CoroutineScope,
    dialogPhaseVisible: MutableState<Boolean>,
    dicesTablePlayer1: MutableState<MutableList<DiceSide>>,
    dicesTablePlayer2: MutableState<MutableList<DiceSide>>,
    viewModel: ViewModelOrlog
) {
    coroutineScope.launch {
        dialogPhaseVisible.value = true
        delay(500L)
        dialogPhaseVisible.value = false
        delay(1500L)
        changePhase(dicesTablePlayer1, dicesTablePlayer2, viewModel)
    }
}

fun changePhase(
    dicesTablePlayer1: MutableState<MutableList<DiceSide>>,
    dicesTablePlayer2: MutableState<MutableList<DiceSide>>,
    viewModel: ViewModelOrlog
) {
    viewModel.phase.value =
        if (dicesTablePlayer1.value.isNotEmpty() || dicesTablePlayer2.value.isNotEmpty())
            Phase.ROLL_PHASE
        else
            if (viewModel.round.value === 1) Phase.RESOLUTION_PHASE
            else Phase.GOD_FAVOR_PHASE
}