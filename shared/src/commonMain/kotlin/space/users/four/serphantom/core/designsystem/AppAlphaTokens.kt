package space.users.four.serphantom.core.designsystem

/**
 * Токены прозрачности — платформенно-нейтральные коэффициенты `0.0…1.0`.
 *
 * Значения соответствуют спецификации Material Design 3. Тип `Float` выбран под
 * основного потребителя — Compose (`Color.copy(alpha = ...)`); в Swift приводится
 * к `Double` для `opacity(_:)`.
 */
object AppAlphaTokens {

    /** Прозрачность контейнера отключённого компонента. */
    const val DISABLED_CONTAINER: Float = 0.12f

    /** Прозрачность содержимого отключённого компонента. */
    const val DISABLED_CONTENT: Float = 0.38f

    /** Прозрачность контейнера «тональной» (вторичной) кнопки. */
    const val TONAL_CONTAINER: Float = 0.12f
}
