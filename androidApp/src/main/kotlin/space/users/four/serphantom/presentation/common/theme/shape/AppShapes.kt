package space.users.four.serphantom.presentation.common.theme.shape

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp
import space.users.four.serphantom.core.designsystem.AppShapeTokens

/**
 * Набор форм приложения в терминах Compose.
 *
 * Compose-отражение общих радиусов `AppShapeTokens` из `shared`. UI читает формы через
 * аксессор `AppTheme.shapes`, а не задаёт `RoundedCornerShape` вручную.
 *
 * @property [extraSmall] Мелкие элементы: чипы, бейджи.
 * @property [small] Кнопки, поля ввода.
 * @property [medium] Карточки, контейнеры.
 * @property [large] Диалоги, крупные карточки.
 * @property [extraLarge] Модальные листы и выделенные поверхности.
 */
@Immutable
data class AppShapes(
    val extraSmall: CornerBasedShape,
    val small: CornerBasedShape,
    val medium: CornerBasedShape,
    val large: CornerBasedShape,
    val extraLarge: CornerBasedShape,
) {

    /** Предопределённые значения [AppShapes], не зависящие от темы. */
    companion object {

        /**
         * Неопределённый набор — прямые углы (нулевой радиус); используется как fallback.
         *
         * Нулевой радиус выбран намеренно вместо `Dp.Unspecified`: последний приводит
         * к некорректной отрисовке скругления.
         */
        @Stable
        val unspecified: AppShapes =
            AppShapes(
                extraSmall = RoundedCornerShape(0.dp),
                small = RoundedCornerShape(0.dp),
                medium = RoundedCornerShape(0.dp),
                large = RoundedCornerShape(0.dp),
                extraLarge = RoundedCornerShape(0.dp),
            )
    }
}

/**
 * Собирает набор форм приложения из общих токенов.
 *
 * @return [AppShapes] с радиусами из `AppShapeTokens`.
 */
fun createAppShapes(): AppShapes =
    AppShapes(
        extraSmall = RoundedCornerShape(AppShapeTokens.CORNER_EXTRA_SMALL.dp),
        small = RoundedCornerShape(AppShapeTokens.CORNER_SMALL.dp),
        medium = RoundedCornerShape(AppShapeTokens.CORNER_MEDIUM.dp),
        large = RoundedCornerShape(AppShapeTokens.CORNER_LARGE.dp),
        extraLarge = RoundedCornerShape(AppShapeTokens.CORNER_EXTRA_LARGE.dp),
    )

/**
 * CompositionLocal форм.
 *
 * Дефолт — fail-fast: ловит UI, забытый снаружи `AppTheme { }`.
 */
val LocalShapes =
    staticCompositionLocalOf<AppShapes> {
        error("AppShapes не предоставлены. Оберните UI в AppTheme { }.")
    }

/**
 * Маппит формы приложения в Material3 [Shapes], чтобы встроенные M3-компоненты
 * наследовали скругления приложения.
 *
 * @return [Shapes] для [androidx.compose.material3.MaterialTheme].
 */
internal fun AppShapes.toMaterialShapes(): Shapes =
    Shapes(
        extraSmall = extraSmall,
        small = small,
        medium = medium,
        large = large,
        extraLarge = extraLarge,
    )
