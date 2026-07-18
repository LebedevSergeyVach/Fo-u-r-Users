package space.users.four.serphantom.presentation.common.theme.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Полный стиль кнопки — все параметры отрисовки в одном месте.
 *
 * Описывать компонент целиком (а не собирать по кусочкам в UI) — способ обеспечить
 * единообразие: у варианта кнопки нет «частично переопределённых» состояний.
 *
 * Подъём хранится как [Dp], а не `ButtonElevation`: последний создаётся только
 * `@Composable`-фабрикой, что помешало бы собирать стили вне композиции. В
 * `ButtonElevation` значение разворачивается на уровне компонента.
 *
 * @property [shape] Форма (скругление) кнопки.
 * @property [colors] Цвета для активного и отключённого состояний.
 * @property [textStyle] Стиль подписи.
 * @property [minHeight] Минимальная высота (область нажатия).
 * @property [iconSize] Размер иконки внутри кнопки.
 * @property [contentPadding] Внутренние отступы содержимого.
 * @property [elevation] Подъём в состоянии покоя или `null`, если тень не нужна.
 */
@Immutable
data class ButtonStyle(
    val shape: CornerBasedShape,
    val colors: ButtonColors,
    val textStyle: TextStyle,
    val minHeight: Dp,
    val iconSize: Dp,
    val contentPadding: PaddingValues,
    val elevation: Dp?,
) {

    /** Предопределённые значения [ButtonStyle], не зависящие от темы. */
    companion object {

        /** Неопределённый стиль — нейтральные значения; используется как fallback. */
        @Stable
        val unspecified: ButtonStyle =
            ButtonStyle(
                shape = RoundedCornerShape(0.dp),
                colors = ButtonColors.unspecified,
                textStyle = TextStyle.Default,
                minHeight = Dp.Unspecified,
                iconSize = Dp.Unspecified,
                contentPadding = PaddingValues(0.dp),
                elevation = null,
            )
    }
}
