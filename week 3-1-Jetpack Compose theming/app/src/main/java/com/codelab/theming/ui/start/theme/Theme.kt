package com.codelab.theming.ui.start.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * darkTheme 여부를 parameter로 받아서, preview 에서도 지정 가능
 */
@Composable
fun JetnewsTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (isDarkTheme) DarkColors else LightColors,
        typography = JetnewsTypography,
        shapes = JetnewsShapes,
        content = content
    )
}

/**
 * lightColors() >> sensible defaults 를 제공해주기 때문에 원하는 컬러만 재정의해서 쓸 수 있음.
 **/
private val LightColors = lightColors(
    primary = Red700,
    primaryVariant = Red900,
    onPrimary = Color.White,
    secondary = Red700,
    secondaryVariant = Red900,
    onSecondary = Color.White,
    error = Red800
)

private val DarkColors = darkColors(
    primary = Red300,
    primaryVariant = Red700,
    onPrimary = Color.Black,
    secondary = Red300,
    onSecondary = Color.Black,
    error = Red200
)