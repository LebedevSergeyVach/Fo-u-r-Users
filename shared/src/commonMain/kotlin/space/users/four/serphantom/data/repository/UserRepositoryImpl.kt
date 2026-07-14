package space.users.four.serphantom.data.repository

import space.users.four.serphantom.core.network.ApiExecutor
import space.users.four.serphantom.core.result.ExecutionResult
import space.users.four.serphantom.core.result.map
import space.users.four.serphantom.data.api.UserApi
import space.users.four.serphantom.data.model.mapper.toDomain
import space.users.four.serphantom.domain.model.User
import space.users.four.serphantom.domain.repository.UserRepository

/**
 * Реализация [UserRepository].
 *
 * Оркестрирует [ApiExecutor] + [UserApi] и маппит DTO → Domain через `.map` на
 * [ExecutionResult]. Никакого `try/catch` — это ответственность [ApiExecutor].
 *
 * @param [userApi] Сетевой API пользователей.
 * @param [apiExecutor] Executor безопасного выполнения сетевых вызовов.
 */
class UserRepositoryImpl(
    private val userApi: UserApi,
    private val apiExecutor: ApiExecutor,
) : UserRepository {

    override suspend fun getUsers(): ExecutionResult<List<User>> =
        apiExecutor
            .execute { userApi.getUsers() }
            .map { dtoList -> dtoList.map { dto -> dto.toDomain() } }
}
