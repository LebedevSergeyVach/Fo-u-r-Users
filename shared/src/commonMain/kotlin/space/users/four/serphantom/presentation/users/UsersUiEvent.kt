package space.users.four.serphantom.presentation.users

/**
 * События пользователя на экране списка пользователей.
 */
sealed interface UsersUiEvent {

    /** Загрузить или обновить список пользователей. */
    data object Refresh : UsersUiEvent
}
