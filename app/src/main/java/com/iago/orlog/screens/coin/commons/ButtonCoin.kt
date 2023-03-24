package com.iago.orlog.screens.coin.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iago.orlog.utils.CoinHeadTail

@Composable
fun ButtonCoin(coin: CoinHeadTail, active: Boolean?, enable: Boolean, onPress: () -> Unit) {

    val color =
        if (active == true) MaterialTheme.colors.primary else MaterialTheme.colors.secondary

    Row(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(10.dp)
            .background(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colors.onBackground,
            )
            .border(
                width = 1.dp,
                shape = MaterialTheme.shapes.medium,
                color = color,
            )
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .clickable {
                if (enable)
                    onPress()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                colorFilter = ColorFilter.tint(color),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(35.dp).padding(bottom = 5.dp),
                painter = painterResource(coin.icon),
            )
            Text(
                color = color,
                text = stringResource(coin.title).uppercase(),
                style = MaterialTheme.typography.h3,
            )
        }
    }
}