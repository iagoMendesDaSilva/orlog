package com.iago.orlog.screens.match

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iago.orlog.R
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.utils.*

@Composable
fun MatchScreen(navController: NavHostController, viewModel: ViewModelOrlog) {

    val dicesPlayer1 = remember { mutableStateOf<MutableList<Dice>>(dices) }
    val dicesPlayer2 = remember { mutableStateOf<MutableList<Dice>>(dices) }

    val dicesSelectedPlayer1 = remember { mutableStateOf<List<DiceSide>>(emptyList()) }
    val dicesSelectedPlayer2 = remember { mutableStateOf<List<DiceSide>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(modifier = Modifier.rotate(180f)) {
            RowDices(dicesPlayer2, dicesSelectedPlayer2)
            RowSelectedDices(dicesSelectedPlayer2.value)
            StatusMatch(viewModel.player2.value)
        }

        MatchDivision(viewModel)

        Column() {
            RowDices(dicesPlayer1, dicesSelectedPlayer1)
            RowSelectedDices(dicesSelectedPlayer1.value)
            StatusMatch(viewModel.player1.value)
        }
    }
}

@Composable
fun MatchDivision(viewModel: ViewModelOrlog) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
                .weight(1f)
                .background(MaterialTheme.colors.secondary)
        ) {}
        Image(
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(90.dp)
                .height(90.dp)
                .padding(horizontal = 10.dp),
            painter = painterResource(viewModel.getImageCoinByTurn()),
        )
        Row(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
                .weight(1f)
                .background(MaterialTheme.colors.secondary)
        ) {}
    }
}

@Composable
fun StatusMatch(player: Player) {
    Row {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 3.dp)
        ) {
            Tokens(player.tokens)
            Gems(player.hp)
        }
        GodsList(
            player.gods,
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 5.dp),
        )
    }
}

@Composable
fun GodsList(testGods: List<God>, modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        testGods.forEach {
            Image(
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(IntrinsicSize.Max)
                    .padding(horizontal = 3.dp),
                painter = painterResource(it.img),
            )
        }
    }
}

@Composable
fun Gems(value: Int) {
    Row(
        modifier = Modifier.padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(35.dp)
                .height(35.dp)
                .padding(end = 8.dp),
            painter = painterResource(R.drawable.gem),
        )
        Text(
            text = stringResource(R.string.gems),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.width(60.dp),
            color = MaterialTheme.colors.secondaryVariant,
        )
        Text(
            text = value.toString().padStart(2, '0'),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary,
        )
    }
}

@Composable
fun Tokens(value: Int) {
    Row(
        modifier = Modifier.padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(35.dp)
                .height(35.dp)
                .padding(end = 8.dp),
            painter = painterResource(R.drawable.token),
        )
        Text(
            text = stringResource(R.string.tokens),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.width(60.dp),
            color = MaterialTheme.colors.secondaryVariant,
        )
        Text(
            text = value.toString().padStart(2, '0'),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary,
        )
    }
}

@Composable
fun RowDices(
    dices: MutableState<MutableList<Dice>>,
    dicesSelectedPlayer: MutableState<List<DiceSide>>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
    ) {
        dices.value.forEach { it ->
            val item = it.sides[(it.sides.indices.random())]
            val favor = it.tokenSides.contains(item.side)
            Image(
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(45.dp)
                    .padding(horizontal = 3.dp)
                    .clickable {
                        var list = mutableListOf<DiceSide>()
                        list.addAll(dicesSelectedPlayer.value)
                        if (favor)
                            item.favor = true
                        list.add(item)
                        dicesSelectedPlayer.value = list

                        var list2 = mutableListOf<Dice>()
                        list2.addAll(dices.value)
                        list2.remove(it)
                        dices.value = list2
                    },
                painter = painterResource(if (favor) item.imgFavor else item.img),
            )
        }
    }
}

@Composable
fun RowSelectedDices(dices: List<DiceSide>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        (0..5).forEachIndexed { index, _ ->
            if (dices.indices.contains(index))
                Image(
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(45.dp)
                        .padding(horizontal = 3.dp),
                    painter = painterResource(
                        if(dices[index].favor) dices[index].imgFavor else dices[index].img
                    ),
                )
            else
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(45.dp)
                        .padding(horizontal = 3.dp)
                        .background(MaterialTheme.colors.onBackground)
                ) {

                }
        }
    }
}