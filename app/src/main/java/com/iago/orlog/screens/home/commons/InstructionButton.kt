package com.iago.orlog.screens.home.commons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iago.orlog.R
import com.iago.orlog.navigation.Screens

@Composable
fun InstructionsButton(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp, end = 25.dp),
        contentAlignment = Alignment.TopEnd,
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            tint = MaterialTheme.colors.secondary,
            contentDescription = stringResource(R.string.info),
            modifier = Modifier
                .size(20.dp)
                .clickable {
                    navController.navigate(Screens.InstructionScreen.name)
                },
        )
    }
}
