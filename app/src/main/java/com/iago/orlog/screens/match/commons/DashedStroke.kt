package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect.Companion.dashPathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun DashedStroke() {
    val strokeColor = MaterialTheme.colors.primary
    val strokeDashed =
        Stroke(width = 1.5f, pathEffect = dashPathEffect(floatArrayOf(10f, 10f), 0f))

    Canvas(Modifier.fillMaxSize(.9f)) {
        drawRoundRect(
            color = strokeColor,
            style = strokeDashed,
            cornerRadius = CornerRadius(x = 5.dp.toPx(), 5.dp.toPx())
        )
    }
}