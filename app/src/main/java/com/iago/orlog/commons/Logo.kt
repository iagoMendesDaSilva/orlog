package com.iago.orlog.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.iago.orlog.R

@Composable
fun Logo(size: Dp, color: Color) {
    Image(
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(color),
        modifier = Modifier.width(size).height(size),
        painter = painterResource(R.drawable.symbol),
        contentDescription = stringResource(id = R.string.symbol)
    )
}