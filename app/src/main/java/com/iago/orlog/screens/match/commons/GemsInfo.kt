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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GemsInfo(value: Int) {
    Row(
        modifier = Modifier.padding(bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            contentDescription = null,
            modifier = Modifier.size(25.dp).padding(end = 5.dp),
            imageVector = Icons.Default.Favorite,
            tint = MaterialTheme.colors.secondary,
        )
        Text(
            fontSize = 14.sp,
            text = value.toString(),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.secondary,
        )
    }
}