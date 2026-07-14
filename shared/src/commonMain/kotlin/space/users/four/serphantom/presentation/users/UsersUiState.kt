package space.users.four.serphantom.presentation.users

import space.users.four.serphantom.domain.model.User

/**
 * Состояние экрана списка пользователей.
 *
 * @param [isLoading] Идёт загрузка.
 * @param [users] Загруженные пользователи (доменные модели).
 * @param [error] Текст ошибки для показа или `null`.
 */
data class UsersUiState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String? = null,
)
