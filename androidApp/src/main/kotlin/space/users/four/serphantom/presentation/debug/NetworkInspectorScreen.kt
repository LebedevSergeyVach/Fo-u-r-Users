package space.users.four.serphantom.presentation.debug

import androidx.compose.runtime.Composable
import io.github.mahmoud.ktorscope.compose.KtorScopeScreen

/**
 * Экран-инспектор сетевых запросов — обёртка над [KtorScopeScreen] из KtorScope.
 *
 * Показывает прямо на устройстве запросы/ответы, заголовки и тела, захваченные
 * плагином KtorScope (см. `HttpClientFactory`) — без подключения к ПК и прокси.
 *
 * @param [onClose] Колбэк закрытия инспектора (нажатие «назад»).
 */
@Composable
fun NetworkInspectorScreen(onClose: () -> Unit) {
    KtorScopeScreen(onBackClicked = onClose)
}
