package space.users.four.serphantom.core.designsystem

/**
 * Размерные токены компонентов — платформенно-нейтральные числа.
 *
 * Значения в независимых от плотности единицах (`dp` на Android, points на iOS).
 * Текущие значения соответствуют спецификации Material Design 3.
 */
object AppSizeTokens {

    /** Минимальная высота кнопки (область нажатия). */
    const val BUTTON_MIN_HEIGHT: Double = 40.0

    /** Размер иконки внутри кнопки. */
    const val BUTTON_ICON_SIZE: Double = 18.0

    /** Горизонтальные отступы содержимого кнопки. */
    const val BUTTON_PADDING_HORIZONTAL: Double = 24.0

    /** Вертикальные отступы содержимого кнопки. */
    const val BUTTON_PADDING_VERTICAL: Double = 10.0

    /** Подъём (тень) кнопки в состоянии покоя. */
    const val BUTTON_ELEVATION: Double = 1.0
}
