package com.iago.orlog.screens.coin.commons

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.utils.Coin

@Composable
fun Coin(
    rotation: Animatable<Float, AnimationVector1D>,
    coinResult: Coin,
    viewModel: ViewModelOrlog,
    size: Dp,
    onPress: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(size)
            .height(size)
            .padding(10.dp)
            .graphicsLayer {
                rotationY = rotation.value
                cameraDistance = 12f * density
            }
            .clickable {
                onPress()
            }
    ) {
        Image(
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            painter = painterResource(viewModel.getImageCoinByTurn(coinResult)),
        )

    }
}