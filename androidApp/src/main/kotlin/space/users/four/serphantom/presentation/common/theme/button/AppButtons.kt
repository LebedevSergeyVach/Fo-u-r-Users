package space.users.four.serphantom.presentation.common.theme.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import space.users.four.serphantom.core.designsystem.AppAlphaTokens
import space.users.four.serphantom.core.designsystem.AppSizeTokens
import space.users.four.serphantom.presentation.common.theme.color.ColorSystem
import space.users.four.serphantom.presentation.common.theme.shape.AppShapes
import space.users.four.serphantom.presentation.common.theme.typography.AppTypography

/**
 * Набор стилей кнопок приложения.
 *
 * @property [primary] Основное действие — заливка акцентом.
 * @property [secondary] Второстепенное действие — тональная заливка.
 * @property [text] Третьестепенное действие — без заливки.
 */
@Immutable
data class AppButtons(
    val primary: ButtonStyle,
    val secondary: ButtonStyle,
    val text: ButtonStyle,
) {

    /** Предопределённые значения [AppButtons], не зависящие от темы. */
    companion object {

        /** Неопределённый набор — все варианты [ButtonStyle.unspecified]. */
        @Stable
        val unspecified: AppButtons =
            AppButtons(
                primary = ButtonStyle.unspecified,
                secondary = ButtonStyle.unspecified,
                text = ButtonStyle.unspecified,
            )
    }
}

/**
 * Собирает стили кнопок из текущих токенов темы.
 *
 * Кнопка соединяет три оси дизайн-системы, поэтому фабрика принимает их явно:
 * цвета задают заливку, формы — скругление, типографика — подпись.
 *
 * @param [colorSystem] Текущая цветовая схема.
 * @param [typography] Текущая типографическая шкала.
 * @param [shapes] Текущий набор форм.
 * @return [AppButtons] с вариантами primary/secondary/text.
 */
fun createAppButtons(
    colorSystem: ColorSystem,
    typography: AppTypography,
    shapes: AppShapes,
): AppButtons {
    val disabledContainer = colorSystem.onSurface.copy(alpha = AppAlphaTokens.DISABLED_CONTAINER)
    val disabledContent = colorSystem.onSurface.copy(alpha = AppAlphaTokens.DISABLED_CONTENT)
    val baseStyle = createBaseButtonStyle(typography = typography, shapes = shapes)

    return AppButtons(
        primary =
            baseStyle.copy(
                colors =
                    ButtonColors(
                        containerColor = colorSystem.primary,
                        contentColor = colorSystem.onPrimary,
                        disabledContainerColor = disabledContainer,
                        disabledContentColor = disabledContent,
                    ),
                elevation = AppSizeTokens.BUTTON_ELEVATION.dp,
            ),
        secondary =
            baseStyle.copy(
                colors =
                    ButtonColors(
                        containerColor = colorSystem.primary.copy(alpha = AppAlphaTokens.TONAL_CONTAINER),
                        contentColor = colorSystem.primary,
                        disabledContainerColor = disabledContainer,
                        disabledContentColor = disabledContent,
                    ),
            ),
        text =
            baseStyle.copy(
                colors =
                    ButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = colorSystem.primary,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = disabledContent,
                    ),
            ),
    )
}

/**
 * Базовый стиль, общий для всех вариантов кнопок.
 *
 * Задаёт геометрию и подпись; цвета и подъём доопределяет конкретный вариант через `copy`.
 *
 * @param [typography] Текущая типографическая шкала.
 * @param [shapes] Текущий набор форм.
 * @return [ButtonStyle] с неопределёнными цветами и без подъёма.
 */
private fun createBaseButtonStyle(
    typography: AppTypography,
    shapes: AppShapes,
): ButtonStyle =
    ButtonStyle(
        shape = shapes.small,
        colors = ButtonColors.unspecified,
        textStyle = typography.labelLarge,
        minHeight = AppSizeTokens.BUTTON_MIN_HEIGHT.dp,
        iconSize = AppSizeTokens.BUTTON_ICON_SIZE.dp,
        contentPadding =
            PaddingValues(
                horizontal = AppSizeTokens.BUTTON_PADDING_HORIZONTAL.dp,
                vertical = AppSizeTokens.BUTTON_PADDING_VERTICAL.dp,
            ),
        elevation = null,
    )

/**
 * CompositionLocal стилей кнопок.
 *
 * Дефолт — fail-fast: ловит UI, забытый снаружи `AppTheme { }`.
 */
val LocalButtons =
    staticCompositionLocalOf<AppButtons> {
        error("AppButtons не предоставлены. Оберните UI в AppTheme { }.")
    }
