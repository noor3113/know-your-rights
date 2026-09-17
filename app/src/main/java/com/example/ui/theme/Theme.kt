package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = OrangePrimary,
    onPrimary = Color.White,
    primaryContainer = OrangeContainer,
    onPrimaryContainer = OnOrangeContainer,
    secondary = OrangeLight,
    onSecondary = Color.Black,
    tertiary = OrangeDark,
    background = DarkBackground,
    onBackground = TextWhite,
    surface = DarkSurface,
    onSurface = TextWhite,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = TextLightGrey,
    outline = DarkBorder,
    error = ErrorRed,
    onError = Color.Black
)

@Composable
fun KnowYourRightsTheme(
    content: @Composable () -> Unit
) {
    // Force the specified Dark Theme with Black & Orange palette
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}

// Retain alias for any existing test compatibility
@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    KnowYourRightsTheme(content = content)
}
