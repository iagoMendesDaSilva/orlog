package com.iago.orlog.screens.instruction

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iago.orlog.R

@Composable
fun InstructionScreen(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
    ) {
        item {
            Title(R.string.instructions)

            SubTitle(text = R.string.roll_phase)
            Content(text = R.string.roll_phase_desc)

            SubTitle(text = R.string.god_favor_phase)
            ContentWithSymbol(
                listOf(
                    R.string.god_favor_phase_desc_pt1,
                    R.string.god_favor_phase_desc_pt2,
                    R.string.god_favor_phase_desc_pt3
                )
            )

            SubTitle(text = R.string.resolution_phase)
            ContentWithSymbol(
                listOf(
                    R.string.resolution_phase_desc_pt1,
                    R.string.resolution_phase_desc_pt2,
                    R.string.resolution_phase_desc_pt3
                )
            )

            SubTitle(text = R.string.dice_effects)
            ContentWithSymbol(
                listOf(
                    R.string.dice_effects_desc_pt1,
                    R.string.dice_effects_desc_pt2
                )
            )

            SubTitle(text = R.string.god_favor)
            ContentWithSymbol(
                listOf(
                    R.string.god_favor_phase_desc_pt1,
                    R.string.god_favor_phase_desc_pt2,
                    R.string.god_favor_phase_desc_pt3
                )
            )
        }
    }
}

@Composable
fun Title(text: Int) {
    Text(
        text = stringResource(text),
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(bottom = 5.dp)
    )
}

@Composable
fun SubTitle(text: Int) {
    Text(
        text = stringResource(text),
        style = MaterialTheme.typography.h2,
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(bottom = 5.dp)
    )
}

@Composable
fun Content(text: Int) {
    Text(
        text = stringResource(text),
        textAlign = TextAlign.Justify,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.secondary,
        modifier = Modifier.padding(bottom = 5.dp)
    )
}

@Composable
fun ContentWithSymbol(texts: List<Int>) {
    Text(
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.secondary,
        textAlign = TextAlign.Justify,
        modifier = Modifier.padding(bottom = 5.dp),
        text = buildAnnotatedString {
            texts.forEachIndexed { index, item ->
                append("${stringResource(item)} ")
                if (index + 1 !== texts.size)
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primary,
                        )
                    ) {
                        append("${stringResource(R.string.symbol)} ")
                    }
            }
        })

}