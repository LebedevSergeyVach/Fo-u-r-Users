package space.users.four.serphantom.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import space.users.four.serphantom.core.network.ApiRoutes
import space.users.four.serphantom.data.model.dto.UserDto

/**
 * Реализация [UserApi] на Ktor [HttpClient].
 *
 * @param [httpClient] Единый настроенный HTTP-клиент приложения.
 */
class UserApiImpl(
    private val httpClient: HttpClient,
) : UserApi {

    override suspend fun getUsers(): List<UserDto> = httpClient.get(ApiRoutes.USERS).body()
}
