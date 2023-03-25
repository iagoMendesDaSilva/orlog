package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iago.orlog.utils.Phase
import kotlinx.coroutines.delay

@Composable
fun GemsInfo(value: Int) {

    val primary = MaterialTheme.colors.primary
    val secondaryColor = MaterialTheme.colors.secondary
    val color = remember { mutableStateOf(secondaryColor) }
    val firstLoad = remember { mutableStateOf(true) }

    LaunchedEffect(key1 = value, block = {
        if (!firstLoad.value) {
            color.value = primary
            delay(1300L)
            color.value = secondaryColor
        }
        firstLoad.value = false
    })

    Row(
        modifier = Modifier.padding(bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            contentDescription = null,
            modifier = Modifier.size(25.dp).padding(end = 5.dp),
            imageVector = Icons.Default.Favorite,
            tint = color.value,
        )
        Text(
            fontSize = 14.sp,
            text = value.toString(),
            fontWeight = FontWeight.Bold,
            color = color.value,
        )
    }
}