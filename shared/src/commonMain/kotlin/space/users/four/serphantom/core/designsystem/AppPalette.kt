package space.users.four.serphantom.core.designsystem

/**
 * Примитивная палитра — сырые цвета в формате ARGB (`0xAARRGGBB`).
 *
 * Платформенно-нейтральные значения: `Long` без привязки к Compose или UIKit.
 * Android оборачивает их в `Color(...)`, iOS — в `SwiftUI.Color`. В UI **не используется
 * напрямую** — только для сборки семантических ролей [ColorRoles].
 *
 * Текущие значения — базовая палитра Material Design 3 (заглушка до появления
 * фирменных цветов бренда).
 */
object AppPalette {

    /** Основной акцент светлой темы (MD3 Primary40). */
    const val PURPLE_40: Long = 0xFF6750A4

    /** Основной акцент тёмной темы (MD3 Primary80). */
    const val PURPLE_80: Long = 0xFFD0BCFF

    /** Контент на акценте тёмной темы (MD3 Primary20). */
    const val PURPLE_20: Long = 0xFF381E72

    /** Чистый белый — контент на акценте светлой темы. */
    const val WHITE: Long = 0xFFFFFFFF

    /** Фон и поверхности светлой темы (MD3 Neutral99). */
    const val NEUTRAL_99: Long = 0xFFFFFBFE

    /** Основной текст светлой темы и фон тёмной (MD3 Neutral10). */
    const val NEUTRAL_10: Long = 0xFF1C1B1F

    /** Основной текст тёмной темы (MD3 Neutral90). */
    const val NEUTRAL_90: Long = 0xFFE6E1E5

    /** Цвет ошибок светлой темы (MD3 Error40). */
    const val RED_40: Long = 0xFFB3261E

    /** Цвет ошибок тёмной темы (MD3 Error80). */
    const val RED_80: Long = 0xFFF2B8B5

    /** Контент на цвете ошибок тёмной темы (MD3 Error10). */
    const val RED_10: Long = 0xFF601410
}
