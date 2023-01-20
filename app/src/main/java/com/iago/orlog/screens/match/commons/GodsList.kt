package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iago.orlog.utils.God

@Composable
fun GodsList(testGods: List<God>, modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        testGods.forEach {
            Image(
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(IntrinsicSize.Max)
                    .padding(horizontal = 3.dp),
                painter = painterResource(it.img),
            )
        }
    }
}