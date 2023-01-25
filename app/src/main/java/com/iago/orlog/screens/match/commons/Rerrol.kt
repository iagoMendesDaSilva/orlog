package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.utils.Player

@Composable
fun Reroll(
    player: MutableState<Player>,
    viewModel: ViewModelOrlog
) {
    Box(
        modifier = Modifier
            .alpha(if (player.value.reroll > 0) 1f else .5f)
            .background(
                MaterialTheme.colors.onBackground,
                MaterialTheme.shapes.large
            )
            .border(
                width = 1.dp,
                MaterialTheme.colors.primary,
                MaterialTheme.shapes.large,
            )
            .padding(10.dp)
            .clickable {
                if (player.value.reroll > 0)
                    viewModel.updatePlayer("reroll", player.value.reroll - 1, player)
            },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            contentDescription = null,
            modifier = Modifier.size(48.dp),
            imageVector = Icons.Default.Replay,
            tint = MaterialTheme.colors.primary,
        )
        Text(
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier= Modifier.padding(top=8.dp),
            color = MaterialTheme.colors.primary,
            text = player.value.reroll.toString(),
        )
    }
}