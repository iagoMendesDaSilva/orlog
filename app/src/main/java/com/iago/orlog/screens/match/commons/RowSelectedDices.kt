package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            if (dices.indices.contains(index))
                Image(
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(55.dp)
                        .height(55.dp)
                        .padding(horizontal = 3.dp),
                    painter = painterResource(
                        if (dices[index].favor) dices[index].imgFavor else dices[index].img
                    ),
                )
            else
                Box(
                    modifier = Modifier
                        .width(55.dp)
                        .height(55.dp)
                        .padding(horizontal = 3.dp)
                        .background(MaterialTheme.colors.onBackground)
                ) {}
        }
    }
}