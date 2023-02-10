package com.iago.orlog.screens.match.commons

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.iago.orlog.R

@Composable
fun PhaseDialog(dialogPhaseVisible: MutableState<Boolean>) {

    val alphaOpacity: Float by animateFloatAsState(
        targetValue = if (dialogPhaseVisible.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000,
            easing = LinearEasing,
        ),
    )

    Box(
        Modifier
            .background(
                color = MaterialTheme.colors.onBackground,
                shape = MaterialTheme.shapes.medium
            )
            .padding(vertical = 25.dp, horizontal = 50.dp)
            .alpha(alphaOpacity),
        contentAlignment = Alignment.Center
    ) {
        Text(
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body2,
            text = stringResource(R.string.god_favor_phase),
        )
    }

}