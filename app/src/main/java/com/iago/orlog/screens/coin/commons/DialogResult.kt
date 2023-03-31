package com.iago.orlog.screens.coin.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.iago.orlog.R
import com.iago.orlog.navigation.Screens
import com.iago.orlog.utils.CoinSide


@Composable
fun DialogResult(
    navController: NavHostController,
    decision: CoinSide,
    coinResult: CoinSide
) {
    val winOrLose = if (decision == coinResult) R.string.won else R.string.lost
    val player = if (decision == coinResult) R.string.you_are_player1 else R.string.you_are_player2

    Dialog(
        onDismissRequest = {},
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .background(
                        color = MaterialTheme.colors.onBackground,
                        shape = MaterialTheme.shapes.medium,
                    )
                    .padding(vertical = 40.dp, horizontal = 25.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Text(
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.secondary,
                    text = stringResource(winOrLose),
                )
                Text(
                    style = MaterialTheme.typography.subtitle2,
                    color = MaterialTheme.colors.primary,
                    text = stringResource(player),
                )
                Spacer(modifier = Modifier.height(25.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .background(
                            shape = MaterialTheme.shapes.large,
                            color = MaterialTheme.colors.onBackground,
                        )
                        .border(
                            width = 1.dp,
                            shape = MaterialTheme.shapes.large,
                            color = MaterialTheme.colors.secondary,
                        )
                        .padding(vertical = 12.dp, horizontal = 20.dp)
                        .clickable { navController.navigate(Screens.GodsScreen.name) },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        textAlign=TextAlign.Center,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondary,
                        text = stringResource(R.string.select_gods_favors),
                    )
                }
            }
        },
    )
}