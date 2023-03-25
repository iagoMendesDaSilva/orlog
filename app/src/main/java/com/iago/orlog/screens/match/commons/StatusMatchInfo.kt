package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.layout.*
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
import com.iago.orlog.commons.Logo
import com.iago.orlog.ui.theme.Error
import com.iago.orlog.utils.StatusInfoMatch
import kotlinx.coroutines.delay
import kotlin.math.abs

@Composable
fun StatusMatchInfo(value: Int, statusInfoMatch: StatusInfoMatch) {

    val secondaryColor = MaterialTheme.colors.secondary
    val primaryColor = MaterialTheme.colors.primary

    val color = remember { mutableStateOf(secondaryColor) }
    val firstLoad = remember { mutableStateOf(true) }
    val previousValue = remember { mutableStateOf(value) }

    val difference = value - previousValue.value
    val differenceFormat = when {
        difference > 0 -> "+ $difference"
        difference < 0 -> "- ${abs(difference)}"
        else -> ""
    }

    LaunchedEffect(key1 = value, block = {
        if (!firstLoad.value) {
            color.value = primaryColor
            delay(1300L)
            color.value = secondaryColor
        }
        firstLoad.value = false
        previousValue.value = value
    })

    Row(
        modifier = Modifier.padding(bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (statusInfoMatch == StatusInfoMatch.TOKENS)
            Box(modifier = Modifier.padding(end = 5.dp)) {
                Logo(size = 18.dp, color.value)
            }
        else
            Icon(
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp)
                    .padding(end = 5.dp),
                imageVector = Icons.Default.Favorite,
                tint = color.value,
            )
        Text(
            fontSize = 14.sp,
            color = color.value,
            fontWeight = FontWeight.Bold,
            text = value.coerceAtLeast(0).toString(),
        )
        if (differenceFormat.isNotEmpty())
            Text(
                fontSize = 12.sp,
                text = differenceFormat,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(start = 5.dp),
                color = MaterialTheme.colors.secondaryVariant,
            )
    }
}