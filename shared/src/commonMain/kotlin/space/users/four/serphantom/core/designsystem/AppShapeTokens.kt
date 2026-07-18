package space.users.four.serphantom.core.designsystem

/**
 * Радиусы скругления — платформенно-нейтральные числа.
 *
 * Значения в независимых от плотности единицах (`dp` на Android, points на iOS).
 * Android оборачивает их в `RoundedCornerShape`, iOS — в `cornerRadius`.
 *
 * Текущая шкала — базовая шкала форм Material Design 3 (заглушка до появления
 * фирменных значений).
 */
object AppShapeTokens {

    /** Мелкие элементы: чипы, бейджи. */
    const val CORNER_EXTRA_SMALL: Double = 4.0

    /** Кнопки, поля ввода. */
    const val CORNER_SMALL: Double = 8.0

    /** Карточки, контейнеры. */
    const val CORNER_MEDIUM: Double = 12.0

    /** Диалоги, крупные карточки. */
    const val CORNER_LARGE: Double = 16.0

    /** Модальные листы и выделенные поверхности. */
    const val CORNER_EXTRA_LARGE: Double = 28.0
}
