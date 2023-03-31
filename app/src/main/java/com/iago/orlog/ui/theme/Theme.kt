package com.iago.orlog.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Yellow,
    background = Black,
    onBackground=DarkGray,
    secondary = White,
    secondaryVariant = Gray
)

private val LightColorPalette = lightColors(
    primary = Yellow,
    background = Black,
    onBackground=DarkGray,
    secondary = White,
    secondaryVariant = Gray
)

@Composable
fun OrlogTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}