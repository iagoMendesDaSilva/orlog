package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iago.orlog.R

@Composable
fun EndTurnButton(
    enable: Boolean,
    onPress: () -> Unit
) {
    Column(
        modifier = Modifier
            .alpha(if (enable) 1f else .5f)
            .fillMaxWidth()
            .height(85.dp)
            .background(
                MaterialTheme.colors.onBackground,
                MaterialTheme.shapes.small
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary,
                shape = MaterialTheme.shapes.small,
            )
            .clickable {
                if (enable)
                    onPress()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            contentDescription = null,
            modifier = Modifier.size(25.dp),
            tint = MaterialTheme.colors.primary,
            imageVector = Icons.Default.HourglassEmpty,
        )
        Text(
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary,
            text = stringResource(id = R.string.end_turn),
        )
    }
}