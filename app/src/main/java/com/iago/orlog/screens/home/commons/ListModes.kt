package com.iago.orlog.screens.home.commons

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.iago.orlog.R
import com.iago.orlog.ViewModelOrlog
import com.iago.orlog.navigation.Screens
import com.iago.orlog.utils.Mode
import com.iago.orlog.utils.gameModes
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ListModes(
    navController: NavHostController,
    viewModel: ViewModelOrlog,
) {
    val context = LocalContext.current
    val pageState = rememberPagerState(pageCount = gameModes.size, initialPage = gameModes.size / 2)
    val coroutineScope = rememberCoroutineScope()

    HorizontalPager(state = pageState) { index ->

        val current = gameModes[index]
        val currentIndex = gameModes.indexOf(current)

        val next = if (currentIndex == gameModes.size - 1) gameModes[0]
        else gameModes[currentIndex + 1]

        val previous = if (currentIndex == 0) gameModes[gameModes.size - 1]
        else gameModes[currentIndex - 1]

        Row(
            modifier = Modifier
                .fillMaxWidth(.9f)
                .height(200.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            GodFavorMode(
                index > 0,
                index,
                previous,
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(.5f)
                    .clickable {
                        coroutineScope.launch {
                            pageState.animateScrollToPage(gameModes.indexOf(previous))
                        }
                    }
            )
            GodFavorMode(
                true,
                index,
                current,
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(1f)
                    .clickable {
                        if (current.enabled) {
                            viewModel.updateMode(current.mode)
                            navController.navigate(Screens.CoinScreen.name)
                        } else {
                            Toast
                                .makeText(context, R.string.coming_soon, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            )
            GodFavorMode(
                index < gameModes.size - 1,
                index,
                next,
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(.5f)
                    .clickable {
                        coroutineScope.launch {
                            pageState.animateScrollToPage(gameModes.indexOf(next))
                        }
                    }
            )

        }
    }
}

@Composable
fun GodFavorMode(show: Boolean, index: Int, current: Mode, modifier: Modifier) {

    val isCenter = (gameModes.indexOf(current) == index)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (show) {
            Image(
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(if (current.enabled) 1f else .5f)
                    .height(if (isCenter) 175.dp else 125.dp),
                painter = painterResource(id = current.icon),
                contentDescription = stringResource(id = current.name),
            )
            Text(
                maxLines = 2,
                style = MaterialTheme.typography.h3,
                text = stringResource(id = current.name),
                modifier = Modifier.alpha(if (current.enabled) 1f else .5f),
                color = if (isCenter) MaterialTheme.colors.secondary else MaterialTheme.colors.secondaryVariant,
            )
        }
    }
}
