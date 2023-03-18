package com.iago.orlog.screens.home.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.iago.orlog.utils.Info
import java.util.*

@Composable
fun InfoDesc(info: Info) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .width(70.dp)
                .height(70.dp)
                .background(MaterialTheme.colors.onBackground, MaterialTheme.shapes.large)
                .border(1.dp, MaterialTheme.colors.secondary, MaterialTheme.shapes.large),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = info.icon,
                modifier = Modifier.size(35.dp),
                tint = MaterialTheme.colors.secondary,
                contentDescription = stringResource(info.title)
            )
        }
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(info.title).uppercase(),
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(top = 8.dp),
            color = MaterialTheme.colors.secondary,
        )
    }
}