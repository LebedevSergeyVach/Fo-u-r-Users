package space.users.four.serphantom.presentation.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import space.users.four.serphantom.presentation.common.theme.color.ColorSystem
import space.users.four.serphantom.presentation.common.theme.color.LocalColorSystem
import space.users.four.serphantom.presentation.common.theme.color.createDarkColorSystem
import space.users.four.serphantom.presentation.common.theme.color.createLightColorSystem
import space.users.four.serphantom.presentation.common.theme.color.toMaterialColorScheme

/**
 * Корневая тема приложения.
 *
 * Собирает токены, раздаёт их через [CompositionLocalProvider] и оборачивает [content]
 * в [MaterialTheme] — чтобы встроенные M3-компоненты наследовали цвета приложения.
 *
 * Светлая/тёмная схема выбирается по [isSystemInDarkTheme]. Принимает **только**
 * [content]: выбор схемы — внутренняя ответственность темы.
 *
 * @param [content] Контент, который получит доступ к теме.
 */
@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val darkTheme = isSystemInDarkTheme()

    val colorSystem =
        remember(darkTheme) {
            if (darkTheme) createDarkColorSystem() else createLightColorSystem()
        }

    CompositionLocalProvider(LocalColorSystem provides colorSystem) {
        MaterialTheme(
            colorScheme = colorSystem.toMaterialColorScheme(darkTheme),
            content = content,
        )
    }
}

/**
 * Единая точка доступа к стилям приложения из Composable-функций.
 *
 * Пример: `AppTheme.colorSystem.primary`. Читать `LocalColorSystem.current` в UI
 * напрямую запрещено — только через этот аксессор.
 */
object AppTheme {

    /** Текущая цветовая схема (light/dark). */
    val colorSystem: ColorSystem
        @Composable
        @ReadOnlyComposable
        get() = LocalColorSystem.current
}
