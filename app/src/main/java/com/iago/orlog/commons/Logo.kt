package com.iago.orlog.commons

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.iago.orlog.R

@Composable
fun Logo() {
    Text(
        fontSize = 200.sp,
        textAlign = TextAlign.Center,
        text = stringResource(R.string.symbol),
        color = MaterialTheme.colors.primary,
    )
}