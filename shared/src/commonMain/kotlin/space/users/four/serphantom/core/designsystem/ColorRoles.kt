package space.users.four.serphantom.core.designsystem

/**
 * Семантические цветовые роли — платформенно-нейтральный контракт темы.
 *
 * Значения — ARGB (`0xAARRGGBB`) из [AppPalette]. Это **единый источник правды** для
 * обеих платформ: Android маппит роли в Compose `ColorSystem`, iOS — в `SwiftUI.Color`.
 * UI работает с ролями, а не с сырой палитрой.
 *
 * @param [primary] Основной акцент.
 * @param [onPrimary] Контент на [primary].
 * @param [background] Фон экранов.
 * @param [onBackground] Контент на [background].
 * @param [surface] Поверхности (карточки, листы).
 * @param [onSurface] Контент на [surface].
 * @param [error] Цвет ошибок.
 * @param [onError] Контент на [error].
 */
data class ColorRoles(
    val primary: Long,
    val onPrimary: Long,
    val background: Long,
    val onBackground: Long,
    val surface: Long,
    val onSurface: Long,
    val error: Long,
    val onError: Long,
)

/**
 * Готовые наборы ролей для светлой и тёмной темы.
 *
 * Используются платформенными фабриками темы; при появлении фирменной палитры
 * достаточно поменять значения здесь — изменения подхватят Android и iOS.
 */
object AppColorRoles {

    /** Роли светлой темы. */
    val light: ColorRoles =
        ColorRoles(
            primary = AppPalette.PURPLE_40,
            onPrimary = AppPalette.WHITE,
            background = AppPalette.NEUTRAL_99,
            onBackground = AppPalette.NEUTRAL_10,
            surface = AppPalette.NEUTRAL_99,
            onSurface = AppPalette.NEUTRAL_10,
            error = AppPalette.RED_40,
            onError = AppPalette.WHITE,
        )

    /** Роли тёмной темы. */
    val dark: ColorRoles =
        ColorRoles(
            primary = AppPalette.PURPLE_80,
            onPrimary = AppPalette.PURPLE_20,
            background = AppPalette.NEUTRAL_10,
            onBackground = AppPalette.NEUTRAL_90,
            surface = AppPalette.NEUTRAL_10,
            onSurface = AppPalette.NEUTRAL_90,
            error = AppPalette.RED_80,
            onError = AppPalette.RED_10,
        )
}
