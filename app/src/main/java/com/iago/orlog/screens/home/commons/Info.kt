package com.iago.orlog.screens.home.commons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.iago.orlog.utils.Info

@Composable
fun InfoDesc(info: Info) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = info.icon,
            modifier = Modifier.size(35.dp),
            tint = MaterialTheme.colors.secondary,
            contentDescription = stringResource(info.title)
        )
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(info.value),
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(top = 5.dp),
            color = MaterialTheme.colors.secondary,
        )
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(info.title),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.secondaryVariant,
        )
    }
}