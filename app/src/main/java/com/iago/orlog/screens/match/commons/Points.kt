package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun Points(value: Int, img:Int, text: Int) {
    Row(
        modifier = Modifier.padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(35.dp)
                .height(35.dp)
                .padding(end = 8.dp),
            painter = painterResource(img),
        )
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.width(60.dp),
            color = MaterialTheme.colors.secondaryVariant,
        )
        Text(
            text = value.toString().padStart(2, '0'),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary,
        )
    }
}