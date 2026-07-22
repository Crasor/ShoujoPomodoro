package com.shoujopomodoro.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Enhanced anime-inspired color palette
val ShoujoPink = Color(0xFFFFB6C1) // Soft pink
val ShoujoLavender = Color(0xFFD8BFD8) // Light lavender
val ShoujoGold = Color(0xFFFFD700) // Gold accent
val ShoujoSkyBlue = Color(0xFFADD8E6) // Sky blue
val ShoujoMint = Color(0xFF98FB98) // Mint green
val ShoujoRose = Color(0xFFF8BBD0) // Rose pink

val ShoujoPurple = Color(0xFFE0BBE4) // Pastel purple
val ShoujoTeal = Color(0xFFA0E4CB) // Teal
val ShoujoAmber = Color(0xFFFFECB3) // Amber

@Composable
fun ShoujoPomodoroEnhancedTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = ShoujoPink,
            onPrimary = Color.Black,
            primaryContainer = ShoujoLavender,
            onPrimaryContainer = Color.Black,
            secondary = ShoujoSkyBlue,
            onSecondary = Color.Black,
            secondaryContainer = ShoujoMint,
            onSecondaryContainer = Color.Black,
            tertiary = ShoujoGold,
            onTertiary = Color.Black,
            tertiaryContainer = ShoujoAmber,
            onTertiaryContainer = Color.Black,
            background = Color(0xFF121212),
            onBackground = Color.White,
            surface = Color(0xFF1E1E1E),
            onSurface = Color.White,
            surfaceVariant = Color(0xFF2D2D2D),
            onSurfaceVariant = Color(0xFFC0C0C0),
            outline = ShoujoLavender.copy(alpha = 0.4f),
            inverseSurface = Color(0xFFE0E0E0),
            inverseOnSurface = Color.Black,
            error = Color(0xFFEFB4B4),
            onError = Color.Black,
            errorContainer = Color(0xFFFFDDD6),
            onErrorContainer = Color.Black,
        )
    } else {
        lightColorScheme(
            primary = ShoujoPink,
            onPrimary = Color.Black,
            primaryContainer = ShoujoLavender,
            onPrimaryContainer = Color.Black,
            secondary = ShoujoSkyBlue,
            onSecondary = Color.Black,
            secondaryContainer = ShoujoMint,
            onSecondaryContainer = Color.Black,
            tertiary = ShoujoGold,
            onTertiary = Color.Black,
            tertiaryContainer = ShoujoAmber,
            onTertiaryContainer = Color.Black,
            background = Color(0xFFF8F8FF),
            onBackground = Color.Black,
            surface = Color.White,
            onSurface = Color.Black,
            surfaceVariant = Color(0xFFF0F0F8),
            onSurfaceVariant = Color(0xFF4D4D4D),
            outline = ShoujoLavender.copy(alpha = 0.4f),
            inverseSurface = Color(0xFF2D2D2D),
            inverseOnSurface = Color(0xFFF8F8FF),
            error = Color(0xFFF8B8B8),
            onError = Color.Black,
            errorContainer = Color(0xFFFFDDD6),
            onErrorContainer = Color.Black,
        )
    }

    ShoujoPomodoroTheme(darkTheme = darkTheme, content = content)
}