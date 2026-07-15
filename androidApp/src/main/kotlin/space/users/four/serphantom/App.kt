package space.users.four.serphantom

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import space.users.four.serphantom.app.AppNavigation
import space.users.four.serphantom.presentation.common.theme.AppTheme

/**
 * Корневой composable Android-приложения.
 *
 * Оборачивает навигацию [AppNavigation] в тему [AppTheme]. Единая точка входа UI,
 * вызывается из [MainActivity].
 */
@Composable
@Preview
fun App() {
    AppTheme {
        AppNavigation()
    }
}
