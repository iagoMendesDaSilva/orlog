package com.iago.orlog.screens.home.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.iago.orlog.R
import com.iago.orlog.navigation.Screens

@Composable
fun Header(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(0.dp, 0.dp, 100.dp, 100.dp),
                color = MaterialTheme.colors.onBackground
            )
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            text = stringResource(R.string.orlog),
            color = MaterialTheme.colors.secondary,
        )
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(R.string.board_game),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary,
        )
        Row(
            modifier = Modifier
                .padding(top = 25.dp)
                .clickable {
                    navController.navigate(Screens.InstructionScreen.name)
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Outlined.Info,
                tint = MaterialTheme.colors.secondary,
                contentDescription = stringResource(R.string.info),
                modifier = Modifier
                    .size(18.dp),
            )
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.how_to_play),
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(start = 5.dp),
                color = MaterialTheme.colors.secondary,
            )
        }
    }
}
