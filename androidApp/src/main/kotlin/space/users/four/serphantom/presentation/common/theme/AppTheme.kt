package space.users.four.serphantom.presentation.common.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

/**
 * Корневая тема приложения (Android).
 *
 * Пока тонкая обёртка над [MaterialTheme]; в дальнейшем разворачивается в
 * токен-систему из `THEME.md` (ColorSystem, типографика, компоненты, dynamic color).
 *
 * @param [content] Контент, оборачиваемый в тему.
 */
@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}
