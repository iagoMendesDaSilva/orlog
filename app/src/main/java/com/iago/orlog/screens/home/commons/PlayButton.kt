package com.iago.orlog.screens.home.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iago.orlog.R

@Composable
fun PlayButton(onPress: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth(.9f)
            .height(70.dp)
            .border(
                width = 2.dp,
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colors.secondary,
            )
            .background(MaterialTheme.colors.onBackground, shape = MaterialTheme.shapes.medium)
            .padding(10.dp)
            .clickable {  }
    ) {
        Text(
            text = stringResource(R.string.play),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(start = 5.dp)
        )
        Icon(
            modifier = Modifier.size(35.dp),
            tint = MaterialTheme.colors.secondary,
            imageVector = Icons.Default.PlayArrow,
            contentDescription = stringResource(id = R.string.play)
        )
    }
}