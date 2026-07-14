package space.users.four.serphantom.domain.usecase

import space.users.four.serphantom.core.result.ExecutionResult
import space.users.four.serphantom.domain.model.User
import space.users.four.serphantom.domain.repository.UserRepository

/**
 * UseCase получения списка пользователей.
 *
 * Единственная точка входа во ViewModel к операции «получить пользователей».
 * Регистрируется в Koin как `single` (синглтон): при необходимости сюда добавляют
 * кэш или общее состояние. Зависит только от `domain`.
 *
 * @param [userRepository] Репозиторий пользователей.
 */
class GetUsersUseCase(
    private val userRepository: UserRepository,
) {

    /**
     * Возвращает список пользователей.
     *
     * @return [ExecutionResult] со списком [User] при успехе или ошибкой.
     */
    suspend operator fun invoke(): ExecutionResult<List<User>> = userRepository.getUsers()
}
