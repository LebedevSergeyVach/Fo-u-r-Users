package space.users.four.serphantom.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * DTO пользователя — модель десериализации ответа сервера.
 *
 * @param [id] Идентификатор пользователя.
 * @param [name] Имя пользователя.
 * @param [email] Электронная почта.
 * @param [avatarUrl] URL аватара; `null`, если сервер не вернул значение.
 */
@Serializable
data class UserDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("avatar_url") val avatarUrl: String? = null,
)
