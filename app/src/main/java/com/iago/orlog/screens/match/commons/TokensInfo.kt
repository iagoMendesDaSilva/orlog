package com.iago.orlog.screens.match.commons

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iago.orlog.commons.Logo
import com.iago.orlog.utils.Phase
import kotlinx.coroutines.delay

@Composable
fun TokensInfo(value: Int) {

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
        Box(modifier = Modifier.padding(end = 5.dp)) {
            Logo(size = 18.dp, color.value)
        }
        Text(
            fontSize = 14.sp,
            text = value.toString(),
            fontWeight = FontWeight.Bold,
            color = color.value,
        )
    }
}