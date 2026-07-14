package space.users.four.serphantom.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import space.users.four.serphantom.core.result.ExecutionResult
import space.users.four.serphantom.domain.usecase.GetUsersUseCase

/**
 * ViewModel экрана списка пользователей.
 *
 * Зависит **только** от [GetUsersUseCase] — про Repository, API и DTO не знает.
 *
 * @param [getUsersUseCase] UseCase загрузки пользователей.
 */
class UsersViewModel(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UsersUiState())

    /** Состояние экрана для подписки из UI. */
    val state: StateFlow<UsersUiState> = _state.asStateFlow()

    init {
        onEvent(UsersUiEvent.Refresh)
    }

    /**
     * Обрабатывает событие экрана и делегирует работу в UseCase.
     *
     * @param [event] Событие пользователя.
     */
    fun onEvent(event: UsersUiEvent) {
        when (event) {
            is UsersUiEvent.Refresh -> loadUsers()
        }
    }

    /** Загружает пользователей в [viewModelScope] и обновляет [state]. */
    private fun loadUsers() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            when (val result = getUsersUseCase()) {
                is ExecutionResult.Success ->
                    _state.update { it.copy(isLoading = false, users = result.data) }

                is ExecutionResult.Error ->
                    _state.update { it.copy(isLoading = false, error = result.message) }
            }
        }
    }
}
