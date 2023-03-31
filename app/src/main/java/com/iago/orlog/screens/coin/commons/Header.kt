package com.iago.orlog.screens.coin.commons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.iago.orlog.R

@Composable
fun Header() {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        Text(
            text = stringResource( R.string.flip_coin),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.primary,
        )
        Text(
            text = stringResource( R.string.coin_desc),
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.secondary,
        )
    }
}