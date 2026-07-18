package space.users.four.serphantom.presentation.common.theme.typography

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle

/**
 * Типографическая шкала приложения в терминах Compose.
 *
 * Compose-отражение общих метрик `AppTypographyTokens` из `shared`. UI читает стили
 * через аксессор `AppTheme.typography`, а не создаёт [TextStyle] вручную.
 *
 * @property [titleLarge] Крупный заголовок экрана.
 * @property [titleMedium] Заголовок блока или карточки.
 * @property [bodyLarge] Основной текст — крупный вариант.
 * @property [bodyMedium] Основной текст — базовый вариант.
 * @property [labelLarge] Подписи кнопок и управляющих элементов.
 */
@Immutable
data class AppTypography(
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val labelLarge: TextStyle,
) {

    /** Предопределённые значения [AppTypography], не зависящие от темы. */
    companion object {

        /** Неопределённая шкала — все стили [TextStyle.Default]; используется как fallback. */
        @Stable
        val unspecified: AppTypography =
            AppTypography(
                titleLarge = TextStyle.Default,
                titleMedium = TextStyle.Default,
                bodyLarge = TextStyle.Default,
                bodyMedium = TextStyle.Default,
                labelLarge = TextStyle.Default,
            )
    }
}
