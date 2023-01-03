package com.iago.orlog.screens.home.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iago.orlog.utils.Mode


@Composable
fun ModeButton(mode: Mode, modeSelected: MutableState<Mode>, disabled: Boolean = false) {

    val selected = mode.mode === modeSelected.value.mode

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .alpha(if (disabled) 0.5f else 1f)
                .width(100.dp)
                .height(100.dp)
                .border(
                    width = 2.dp,
                    shape = MaterialTheme.shapes.medium,
                    color =
                    if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.secondaryVariant,
                )
                .background(MaterialTheme.colors.onBackground, shape = MaterialTheme.shapes.medium)
                .padding(10.dp)
                .clickable {
                    if (!disabled)
                        modeSelected.value = mode
                }
        ) {
            Icon(
                modifier = Modifier.size(35.dp),
                tint = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.secondaryVariant,
                imageVector = mode.icon,
                contentDescription = stringResource(mode.name)
            )
        }
        Text(
            text = stringResource(mode.name),
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(top = 5.dp)
                .alpha(if (disabled) 0.5f else 1f),
            color = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.secondaryVariant,
        )
    }
}