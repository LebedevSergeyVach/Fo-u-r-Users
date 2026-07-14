package space.users.four.serphantom.presentation.users

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import space.users.four.serphantom.domain.model.User

/**
 * Экран списка пользователей, подключённый к [UsersViewModel].
 *
 * Получает ViewModel из Koin и собирает состояние; вся логика — во ViewModel.
 *
 * @param [viewModel] ViewModel экрана (по умолчанию из Koin).
 */
@Composable
fun UsersScreen(viewModel: UsersViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    UsersScreenContent(
        state = state,
        onEvent = viewModel::onEvent,
    )
}

/**
 * Stateless-контент экрана — удобен для `@Preview`.
 *
 * @param [state] Состояние экрана.
 * @param [onEvent] Обработчик событий пользователя.
 * @param [modifier] Модификатор корневого контейнера.
 */
@Composable
private fun UsersScreenContent(
    state: UsersUiState,
    onEvent: (UsersUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()
            state.error != null -> Text(text = state.error)
            else ->
                LazyColumn {
                    items(state.users) { user -> UserRow(user = user) }
                }
        }
    }
}

/**
 * Строка списка с данными одного [User].
 *
 * @param [user] Пользователь для отображения.
 */
@Composable
private fun UserRow(user: User) {
    Text(text = user.name)
}
