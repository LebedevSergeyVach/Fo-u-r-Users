package space.users.four.serphantom.domain.repository

import space.users.four.serphantom.core.result.ExecutionResult
import space.users.four.serphantom.domain.model.User

/**
 * Контракт доступа к данным пользователей.
 *
 * Живёт в `domain` и не зависит от источников данных. Реализация — в `data/repository`.
 * Вызывается **только** из слоя UseCase.
 *
 * @see [space.users.four.serphantom.domain.usecase.GetUsersUseCase]
 */
interface UserRepository {

    /**
     * Возвращает список пользователей.
     *
     * @return [ExecutionResult] со списком [User] при успехе или ошибкой.
     */
    suspend fun getUsers(): ExecutionResult<List<User>>
}
