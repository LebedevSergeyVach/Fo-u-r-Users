package space.users.four.serphantom.presentation.common.theme.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import space.users.four.serphantom.core.designsystem.AppColorRoles
import space.users.four.serphantom.core.designsystem.ColorRoles

/**
 * Светлая цветовая схема — из общих ролей `AppColorRoles.light`.
 *
 * @return [ColorSystem] светлой темы.
 */
fun createLightColorSystem(): ColorSystem = AppColorRoles.light.toColorSystem()

/**
 * Тёмная цветовая схема — из общих ролей `AppColorRoles.dark`.
 *
 * @return [ColorSystem] тёмной темы.
 */
fun createDarkColorSystem(): ColorSystem = AppColorRoles.dark.toColorSystem()

/**
 * Оборачивает платформенно-нейтральные ARGB-роли в Compose-цвета.
 *
 * @return [ColorSystem] с теми же ролями в виде [Color].
 */
private fun ColorRoles.toColorSystem(): ColorSystem =
    ColorSystem(
        primary = Color(primary),
        onPrimary = Color(onPrimary),
        background = Color(background),
        onBackground = Color(onBackground),
        surface = Color(surface),
        onSurface = Color(onSurface),
        error = Color(error),
        onError = Color(onError),
    )

/**
 * CompositionLocal цветовой схемы.
 *
 * `staticCompositionLocalOf` — тема меняется редко, fine-grained подписки не нужны.
 * Дефолт — fail-fast: ловит UI, забытый снаружи `AppTheme { }`.
 */
val LocalColorSystem =
    staticCompositionLocalOf<ColorSystem> {
        error("ColorSystem не предоставлен. Оберните UI в AppTheme { }.")
    }

/**
 * Маппит семантические роли в Material3 [ColorScheme], чтобы встроенные M3-компоненты
 * (ripple, `TextField`, диалоги) наследовали цвета приложения.
 *
 * Роли, не описанные в [ColorSystem], берутся из базовой схемы M3 по [darkTheme].
 *
 * @param [darkTheme] `true` — базой служит `darkColorScheme()`, иначе `lightColorScheme()`.
 * @return [ColorScheme] для [androidx.compose.material3.MaterialTheme].
 */
internal fun ColorSystem.toMaterialColorScheme(darkTheme: Boolean): ColorScheme =
    if (darkTheme) {
        darkColorScheme(
            primary = primary,
            onPrimary = onPrimary,
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            error = error,
            onError = onError,
        )
    } else {
        lightColorScheme(
            primary = primary,
            onPrimary = onPrimary,
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            error = error,
            onError = onError,
        )
    }
