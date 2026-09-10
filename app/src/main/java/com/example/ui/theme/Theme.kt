package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = ScoutBrown80,
    onPrimary = ScoutBrownOnContainer,
    primaryContainer = ScoutBrownPrimary,
    onPrimaryContainer = ScoutBrown80,
    secondary = ScoutRed80,
    onSecondary = ScoutRedOnContainer,
    secondaryContainer = ScoutRedSecondary,
    onSecondaryContainer = ScoutRed80,
    tertiary = ScoutAmber80,
    onTertiary = ScoutGoldOnContainer,
    background = Color(0xFF1E1B18),
    surface = Color(0xFF272320),
    surfaceVariant = Color(0xFF352F2B),
    onBackground = Color(0xFFEDE0D4),
    onSurface = Color(0xFFEDE0D4),
    outline = ScoutOutline
  )

private val LightColorScheme =
  lightColorScheme(
    primary = ScoutBrownPrimary,
    onPrimary = ScoutBrownOnPrimary,
    primaryContainer = ScoutBrownContainer,
    onPrimaryContainer = ScoutBrownOnContainer,
    secondary = ScoutRedSecondary,
    onSecondary = ScoutRedOnSecondary,
    secondaryContainer = ScoutRedContainer,
    onSecondaryContainer = ScoutRedOnContainer,
    tertiary = ScoutGoldTertiary,
    onTertiary = Color.White,
    tertiaryContainer = ScoutGoldContainer,
    onTertiaryContainer = ScoutGoldOnContainer,
    background = ScoutCreamBg,
    surface = ScoutSurface,
    surfaceVariant = ScoutSurfaceVariant,
    onBackground = Color(0xFF201A18),
    onSurface = Color(0xFF201A18),
    outline = ScoutOutline
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
