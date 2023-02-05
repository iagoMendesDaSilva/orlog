package com.iago.orlog.screens.coin.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun ButtonCoin(text: Int, active: Boolean?, enable: Boolean, onPress: () -> Unit) {

    val color =
        if (active == true) MaterialTheme.colors.primary else MaterialTheme.colors.secondary

    Row(
        modifier = Modifier
            .width(150.dp)
            .padding(horizontal = 10.dp)
            .background(
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colors.onBackground,
            )
            .border(
                width = 1.dp,
                shape = MaterialTheme.shapes.large,
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
        Text(
            color = color,
            text = stringResource(text),
            style = MaterialTheme.typography.body2,
        )
    }
}