package com.iago.orlog.screens.coin.commons

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.iago.orlog.utils.Coin
import com.iago.orlog.utils.Coins

@Composable
fun Coin(
    rotation: Animatable<Float, AnimationVector1D>,
    coinResult: Coin,
    size: Dp,
) {

    val imageWhileRunning =if (rotation.value < 100f)
        Coins.tail.image
    else Coins.head.image

    val imageDecision =if (coinResult == Coin.FACE_DOWN)
        Coins.tail.image
    else Coins.head.image

    Box(
        modifier = Modifier
            .width(size)
            .height(size)
            .padding(10.dp)
            .graphicsLayer {
                rotationY = rotation.value
                cameraDistance = 24f * density
            }
    ) {
        Image(
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            painter = painterResource(
                if(rotation.isRunning) imageWhileRunning else imageDecision
            ),
        )

    }
}