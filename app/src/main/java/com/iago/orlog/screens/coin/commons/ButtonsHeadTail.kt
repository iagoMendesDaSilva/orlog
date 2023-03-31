package com.iago.orlog.screens.coin.commons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.iago.orlog.utils.CoinSide
import com.iago.orlog.utils.Coins

@Composable
fun ButtonsHeadNTail(decision: MutableState<CoinSide>) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        ButtonCoin(
            coin = Coins.head,
            active = decision.value == CoinSide.FACE_UP,
            enable = decision.value == CoinSide.UNDEFINED,
        ) {
            decision.value = CoinSide.FACE_UP
        }
        ButtonCoin(
            coin = Coins.tail,
            active = decision.value == CoinSide.FACE_DOWN,
            enable = decision.value == CoinSide.UNDEFINED,
        ) {
            decision.value = CoinSide.FACE_DOWN
        }
    }
}