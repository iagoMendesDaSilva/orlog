package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iago.orlog.commons.Logo

@Composable
fun TokensInfo(value: Int) {
    Row(
        modifier = Modifier.padding(bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(modifier = Modifier.padding(end = 5.dp)){
            Logo(size = 18.dp, MaterialTheme.colors.secondary)
        }
        Text(
            fontSize = 14.sp,
            text = value.toString(),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.secondary,
        )
    }
}