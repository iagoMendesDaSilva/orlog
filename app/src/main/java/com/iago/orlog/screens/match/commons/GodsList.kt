package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iago.orlog.utils.God

@Composable
fun GodsList(rotate: Boolean, godFavors: List<God>, modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        godFavors.forEach { godFavor ->

            val openDialog = remember { mutableStateOf(false) }

            if (openDialog.value)
                GodInfoMatch(godFavor, openDialog, rotate){}
            Image(
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(85.dp)
                    .padding(horizontal = 3.dp).clickable {
                        openDialog.value = true
                    },
                painter = painterResource(godFavor.img),
            )
        }
    }
}