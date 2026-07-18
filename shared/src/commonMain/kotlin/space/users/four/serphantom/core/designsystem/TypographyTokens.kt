package space.users.four.serphantom.core.designsystem

/**
 * Метрики одного стиля текста — платформенно-нейтральные числа.
 *
 * Размеры заданы в масштабируемых единицах (`sp` на Android, points на iOS).
 * Android оборачивает их в `TextStyle`, iOS — в `SwiftUI.Font`.
 *
 * @param [fontSize] Кегль шрифта.
 * @param [lineHeight] Межстрочный интервал.
 * @param [letterSpacing] Межбуквенный интервал.
 * @param [fontWeight] Насыщенность начертания в терминах CSS (400 — обычный, 500 — средний).
 */
data class TextStyleTokens(
    val fontSize: Double,
    val lineHeight: Double,
    val letterSpacing: Double,
    val fontWeight: Int,
)

/**
 * Типографическая шкала приложения.
 *
 * Значения — базовая шкала Material Design 3 (заглушка до появления фирменной
 * типографики). Меняются здесь один раз — подхватывают обе платформы.
 */
object AppTypographyTokens {

    /** Крупный заголовок экрана. */
    val titleLarge: TextStyleTokens =
        TextStyleTokens(fontSize = 22.0, lineHeight = 28.0, letterSpacing = 0.0, fontWeight = 400)

    /** Заголовок блока или карточки. */
    val titleMedium: TextStyleTokens =
        TextStyleTokens(fontSize = 16.0, lineHeight = 24.0, letterSpacing = 0.15, fontWeight = 500)

    /** Основной текст — крупный вариант. */
    val bodyLarge: TextStyleTokens =
        TextStyleTokens(fontSize = 16.0, lineHeight = 24.0, letterSpacing = 0.5, fontWeight = 400)

    /** Основной текст — базовый вариант. */
    val bodyMedium: TextStyleTokens =
        TextStyleTokens(fontSize = 14.0, lineHeight = 20.0, letterSpacing = 0.25, fontWeight = 400)

    /** Подписи кнопок и управляющих элементов. */
    val labelLarge: TextStyleTokens =
        TextStyleTokens(fontSize = 14.0, lineHeight = 20.0, letterSpacing = 0.1, fontWeight = 500)
}
