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
import space.users.four.serphantom.presentation.common.theme.shape.AppShapes
import space.users.four.serphantom.presentation.common.theme.shape.LocalShapes
import space.users.four.serphantom.presentation.common.theme.shape.createAppShapes
import space.users.four.serphantom.presentation.common.theme.shape.toMaterialShapes
import space.users.four.serphantom.presentation.common.theme.typography.AppTypography
import space.users.four.serphantom.presentation.common.theme.typography.LocalTypography
import space.users.four.serphantom.presentation.common.theme.typography.createAppTypography
import space.users.four.serphantom.presentation.common.theme.typography.toMaterialTypography

/**
 * Корневая тема приложения.
 *
 * Собирает токены, раздаёт их через [CompositionLocalProvider] и оборачивает [content]
 * в [MaterialTheme] — чтобы встроенные M3-компоненты наследовали стили приложения.
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
    val typography = remember { createAppTypography() }
    val shapes = remember { createAppShapes() }

    CompositionLocalProvider(
        LocalColorSystem provides colorSystem,
        LocalTypography provides typography,
        LocalShapes provides shapes,
    ) {
        MaterialTheme(
            colorScheme = colorSystem.toMaterialColorScheme(darkTheme),
            typography = typography.toMaterialTypography(),
            shapes = shapes.toMaterialShapes(),
            content = content,
        )
    }
}

/**
 * Единая точка доступа к стилям приложения из Composable-функций.
 *
 * Пример: `AppTheme.colorSystem.primary`, `AppTheme.typography.titleLarge`. Читать
 * `LocalColorSystem.current` / `LocalTypography.current` в UI напрямую запрещено —
 * только через этот аксессор.
 */
object AppTheme {

    /** Текущая цветовая схема (light/dark). */
    val colorSystem: ColorSystem
        @Composable
        @ReadOnlyComposable
        get() = LocalColorSystem.current

    /** Текущая типографическая шкала. */
    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    /** Текущий набор форм (скруглений). */
    val shapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current
}
