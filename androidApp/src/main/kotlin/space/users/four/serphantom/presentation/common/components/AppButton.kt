package space.users.four.serphantom.presentation.common.components

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import space.users.four.serphantom.presentation.common.theme.AppTheme
import space.users.four.serphantom.presentation.common.theme.button.ButtonStyle

/**
 * Кнопка приложения — отрисовка по стилю дизайн-системы.
 *
 * Все параметры внешнего вида берутся из [style], поэтому вызывающему коду не нужно
 * (и не следует) задавать цвета, форму или отступы вручную. Варианты выбираются как
 * `AppTheme.buttons.primary` / `.secondary` / `.text`.
 *
 * @param [text] Подпись кнопки.
 * @param [onClick] Обработчик нажатия.
 * @param [modifier] Модификатор компоновки.
 * @param [style] Стиль из дизайн-системы; по умолчанию — основное действие.
 * @param [enabled] `false` — кнопка отключена и использует disabled-цвета [style].
 */
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: ButtonStyle = AppTheme.buttons.primary,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = style.minHeight),
        enabled = enabled,
        shape = style.shape,
        colors =
            ButtonDefaults.buttonColors(
                containerColor = style.colors.containerColor,
                contentColor = style.colors.contentColor,
                disabledContainerColor = style.colors.disabledContainerColor,
                disabledContentColor = style.colors.disabledContentColor,
            ),
        elevation =
            style.elevation?.let { elevation ->
                ButtonDefaults.buttonElevation(defaultElevation = elevation)
            },
        contentPadding = style.contentPadding,
    ) {
        Text(
            text = text,
            style = style.textStyle,
        )
    }
}
