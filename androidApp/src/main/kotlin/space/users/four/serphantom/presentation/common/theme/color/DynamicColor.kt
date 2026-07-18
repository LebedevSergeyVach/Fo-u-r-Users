package space.users.four.serphantom.presentation.common.theme.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

/**
 * Использовать ли системный акцент (Material You) вместо фирменной палитры.
 *
 * Сейчас включено: фирменных цветов бренда ещё нет, палитра — заглушка MD3, и системный
 * акцент даёт более живой результат. **Когда появится фирменная палитра**, значение
 * стоит переключить на `false` (либо вынести в пользовательскую настройку/Remote Config),
 * иначе системный акцент будет перекрывать брендовые цвета.
 */
internal const val DYNAMIC_COLOR_ENABLED: Boolean = true

/**
 * Системная (dynamic) цветовая схема или `null`, если она отключена конфигурацией.
 *
 * Проверка версии Android намеренно отсутствует: `minSdk` проекта — 33, а Material You
 * доступен с API 31, поэтому системный акцент есть на всех поддерживаемых устройствах.
 * `null` здесь означает «выключено через [DYNAMIC_COLOR_ENABLED]», а не «не поддерживается».
 *
 * @param [darkTheme] Нужна ли тёмная схема.
 * @return [ColorSystem] системного акцента или `null` → фолбэк на фирменную палитру.
 */
@Composable
internal fun dynamicColorSystemOrNull(darkTheme: Boolean): ColorSystem? {
    if (!DYNAMIC_COLOR_ENABLED) return null

    val context = LocalContext.current

    return remember(darkTheme, context) {
        val scheme =
            if (darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        scheme.toColorSystem()
    }
}

/**
 * Сужает Material3 [ColorScheme] до семантических ролей приложения.
 *
 * @return [ColorSystem] с ролями, взятыми из системной схемы.
 */
private fun ColorScheme.toColorSystem(): ColorSystem =
    ColorSystem(
        primary = primary,
        onPrimary = onPrimary,
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        error = error,
        onError = onError,
    )
