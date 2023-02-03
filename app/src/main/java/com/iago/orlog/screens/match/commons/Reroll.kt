package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iago.orlog.R
import com.iago.orlog.utils.DiceSide
import com.iago.orlog.utils.Player

@Composable
fun Reroll(
    player: MutableState<Player>,
    dicesTablePlayer: MutableList<DiceSide>,
    onPress: () -> Unit
) {
    val enable = player.value.reroll > 0 && dicesTablePlayer.size > 0
    Row(
        modifier = Modifier
            .alpha(if (enable) 1f else .5f)
            .background(
                MaterialTheme.colors.onBackground,
                MaterialTheme.shapes.small
            )
            .border(
                width = 1.dp,
                MaterialTheme.colors.primary,
                MaterialTheme.shapes.small,
            )
            .padding(start =20.dp,end= 20.dp, top = 10.dp, bottom = 10.dp)
            .clickable {
                if (enable)
                    onPress()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            contentScale = ContentScale.Fit,
            contentDescription = null,
            painter = painterResource(R.drawable.cube),
            modifier = Modifier.width(30.dp).height(30.dp).padding(end = 5.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
                text = stringResource(id = R.string.roll_again),
            )
            Text(
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.primary,
                text = stringResource(id = R.string.roll_remaining, player.value.reroll),
            )
        }
    }
}