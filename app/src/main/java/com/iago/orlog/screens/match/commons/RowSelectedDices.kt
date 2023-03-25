package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iago.orlog.utils.DiceSide

@Composable
fun RowSelectedDices(dices: List<DiceSide>) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp *.85f

    val padding = with(LocalDensity.current) {
        screenWidth * 0.01f
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        (0..5).forEachIndexed { index, _ ->
            Box(
                modifier = Modifier
                    .width(screenWidth/6)
                    .height(screenWidth/6)
                    .aspectRatio(1f)
                    .padding(padding)
                    .background(MaterialTheme.colors.onBackground, RoundedCornerShape(5.dp)),
                contentAlignment = Alignment.Center,
            ) {
                if (dices.indices.contains(index)) {
                    if (dices[index].favor)
                        DashedStroke()
                    Image(
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(5.dp)),
                        painter = painterResource(dices[index].img),
                    )
                }
            }
        }
    }
}