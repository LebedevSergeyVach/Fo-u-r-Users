package space.users.four.serphantom.presentation.common.theme.typography

import androidx.compose.material3.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import space.users.four.serphantom.core.designsystem.AppTypographyTokens
import space.users.four.serphantom.core.designsystem.TextStyleTokens

/**
 * Собирает типографическую шкалу приложения из общих токенов.
 *
 * @return [AppTypography] с метриками из `AppTypographyTokens`.
 */
fun createAppTypography(): AppTypography =
    AppTypography(
        titleLarge = AppTypographyTokens.titleLarge.toTextStyle(),
        titleMedium = AppTypographyTokens.titleMedium.toTextStyle(),
        bodyLarge = AppTypographyTokens.bodyLarge.toTextStyle(),
        bodyMedium = AppTypographyTokens.bodyMedium.toTextStyle(),
        labelLarge = AppTypographyTokens.labelLarge.toTextStyle(),
    )

/**
 * Оборачивает платформенно-нейтральные метрики в Compose-[TextStyle].
 *
 * @return [TextStyle] с кеглем, интервалами и насыщенностью из токенов.
 */
private fun TextStyleTokens.toTextStyle(): TextStyle =
    TextStyle(
        fontSize = fontSize.sp,
        lineHeight = lineHeight.sp,
        letterSpacing = letterSpacing.sp,
        fontWeight = FontWeight(fontWeight),
    )

/**
 * CompositionLocal типографики.
 *
 * Дефолт — fail-fast: ловит UI, забытый снаружи `AppTheme { }`.
 */
val LocalTypography =
    staticCompositionLocalOf<AppTypography> {
        error("AppTypography не предоставлена. Оберните UI в AppTheme { }.")
    }

/**
 * Маппит шкалу приложения в Material3 [Typography], чтобы встроенные M3-компоненты
 * наследовали типографику приложения.
 *
 * Стили, не описанные в [AppTypography], берутся из базовой шкалы M3.
 *
 * @return [Typography] для [androidx.compose.material3.MaterialTheme].
 */
internal fun AppTypography.toMaterialTypography(): Typography =
    Typography(
        titleLarge = titleLarge,
        titleMedium = titleMedium,
        bodyLarge = bodyLarge,
        bodyMedium = bodyMedium,
        labelLarge = labelLarge,
    )
