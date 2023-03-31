package com.iago.orlog.screens.home.commons

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.iago.orlog.R

@Composable
fun Notification() {

    var expanded by remember { mutableStateOf(false) }
    val transition = updateTransition(expanded, label = "")
    val height by transition.animateDp(label = "") { if (it) 110.dp else 70.dp }

    Row(
        modifier = Modifier
            .fillMaxWidth(.75f)
            .height(height)
            .background(MaterialTheme.colors.onBackground, MaterialTheme.shapes.medium)
            .border(1.dp, MaterialTheme.colors.secondary, MaterialTheme.shapes.medium)
            .padding(vertical = 10.dp, horizontal = 15.dp)
            .clickable { expanded = !expanded },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            contentDescription = stringResource(id = R.string.notification),
            modifier = Modifier.size(22.dp),
            tint = MaterialTheme.colors.secondary,
            imageVector = Icons.Default.Notifications,
        )
        Column(modifier = Modifier.padding(horizontal = 7.dp)) {
            Text(
                maxLines = 1,
                text = stringResource(R.string.greetings),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.secondary,
            )
            Text(
                modifier = Modifier.fillMaxWidth(.9f),
                maxLines = if (expanded) 3 else 1,
                overflow = TextOverflow.Ellipsis,
                text = stringResource(R.string.welcome),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.secondary,
            )
        }
        Icon(
            contentDescription = stringResource(id = R.string.arrow),
            modifier = Modifier.size(22.dp),
            tint = MaterialTheme.colors.secondary,
            imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
        )
    }
}
