package com.iago.orlog.screens.gods.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iago.orlog.R


@Composable
fun ButtonConfirmGods(active: Boolean, onPress: () -> Unit) {
    Row(
        modifier = Modifier
            .background(
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colors.onBackground,
            )
            .border(
                width = 1.dp,
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colors.secondary,
            )
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .clickable {
                if (active)
                    onPress()
            }
            .alpha(if (active) 1f else 0.5f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            contentDescription = null,
            imageVector = Icons.Outlined.Check,
            tint = MaterialTheme.colors.secondary,
            modifier = Modifier
                .padding(end = 5.dp)
                .size(18.dp)
        )
        Text(
            text = stringResource(R.string.confirm),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.secondary,
        )
    }
}