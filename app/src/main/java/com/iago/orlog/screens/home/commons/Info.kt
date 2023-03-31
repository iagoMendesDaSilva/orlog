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
import androidx.compose.ui.unit.dp
import com.iago.orlog.utils.Info

@Composable
fun InfoDesc(info: Info) {
    Column(
        modifier=Modifier.width(130.dp).padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
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
            maxLines=1,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(info.title).uppercase(),
        )
    }
}