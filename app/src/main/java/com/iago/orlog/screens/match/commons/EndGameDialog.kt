package com.iago.orlog.screens.match.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.iago.orlog.R
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.navigation.Screens
import com.iago.orlog.utils.Player
import kotlinx.coroutines.delay


@Composable
fun EndGameDialog(
    navController: NavHostController,
    winner: MutableState<Player?>,
    viewModel: ViewModelOrlog
) {

    LaunchedEffect(key1 = true, block = {
        delay(2000L)
        winner.value = null
        viewModel.resetGame()
        navController.navigate(Screens.HomeScreen.name)
    })

    Dialog(
        onDismissRequest = {  },
        content = {
            if(winner.value!=null)
                Column(
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .background(
                            color = MaterialTheme.colors.onBackground,
                            shape = MaterialTheme.shapes.medium,
                        )
                        .padding(horizontal = 25.dp, vertical = 50.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Text(
                        text = stringResource(id = if(winner.value!!.ia) R.string.lost else R.string.won),
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.secondary,
                    )
                }
        },
    )
}