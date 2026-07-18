package space.users.four.serphantom.presentation.common.theme.button

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

/**
 * Цветовая схема кнопки для активного и отключённого состояний.
 *
 * Собственный тип дизайн-системы — не путать с `androidx.compose.material3.ButtonColors`,
 * в который он маппится на уровне компонента.
 *
 * @property [containerColor] Цвет фона в активном состоянии.
 * @property [contentColor] Цвет содержимого (текст, иконки) в активном состоянии.
 * @property [disabledContainerColor] Цвет фона в отключённом состоянии.
 * @property [disabledContentColor] Цвет содержимого в отключённом состоянии.
 */
@Immutable
data class ButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
) {

    /** Предопределённые значения [ButtonColors], не зависящие от темы. */
    companion object {

        /** Неопределённая схема — все цвета [Color.Unspecified]; используется как fallback. */
        @Stable
        val unspecified: ButtonColors =
            ButtonColors(
                containerColor = Color.Unspecified,
                contentColor = Color.Unspecified,
                disabledContainerColor = Color.Unspecified,
                disabledContentColor = Color.Unspecified,
            )
    }
}
