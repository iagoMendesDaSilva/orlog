package com.iago.orlog.screens.coin.commons

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.utils.COIN

@Composable
fun Coin(
    rotation: Animatable<Float, AnimationVector1D>,
    coinResult: COIN,
    viewModel: ViewModelOrlog,
    onPress: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
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