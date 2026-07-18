package space.users.four.serphantom.presentation.common.theme.color

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

/**
 * Семантические цветовые роли приложения в терминах Compose (light/dark/dynamic).
 *
 * Compose-отражение общего контракта `ColorRoles` из `shared`. Именно этот тип читает
 * UI — через аксессор `AppTheme.colorSystem`, а не сырую палитру.
 *
 * @property [primary] Основной акцент.
 * @property [onPrimary] Контент на [primary].
 * @property [background] Фон экранов.
 * @property [onBackground] Контент на [background].
 * @property [surface] Поверхности (карточки, листы).
 * @property [onSurface] Контент на [surface].
 * @property [error] Цвет ошибок.
 * @property [onError] Контент на [error].
 */
@Immutable
data class ColorSystem(
    val primary: Color,
    val onPrimary: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val error: Color,
    val onError: Color,
) {

    /** Предопределённые значения [ColorSystem], не зависящие от темы. */
    companion object {

        /** Неопределённая схема — все роли [Color.Unspecified]; используется как fallback. */
        @Stable
        val unspecified: ColorSystem =
            ColorSystem(
                primary = Color.Unspecified,
                onPrimary = Color.Unspecified,
                background = Color.Unspecified,
                onBackground = Color.Unspecified,
                surface = Color.Unspecified,
                onSurface = Color.Unspecified,
                error = Color.Unspecified,
                onError = Color.Unspecified,
            )
    }
}
