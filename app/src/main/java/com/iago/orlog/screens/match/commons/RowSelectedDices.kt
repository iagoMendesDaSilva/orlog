package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iago.orlog.utils.DiceSide

@Composable
fun RowSelectedDices(dices: List<DiceSide>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .height(45.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        (0..5).forEachIndexed { index, _ ->
            Box(
                modifier = Modifier
                    .width(55.dp)
                    .height(55.dp)
                    .padding(horizontal = 3.dp)
                    .background(MaterialTheme.colors.onBackground, RoundedCornerShape(5.dp)),
                contentAlignment = Alignment.Center,
            ) {
                if (dices.indices.contains(index)) {
                    if (dices[index].favor)
                        DashedStroke()
                    Image(
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(5.dp)),
                        painter = painterResource(dices[index].img),
                    )
                }
            }
        }
    }
}