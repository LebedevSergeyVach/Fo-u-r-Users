package space.users.four.serphantom.data.model.mapper

import space.users.four.serphantom.data.model.dto.UserDto
import space.users.four.serphantom.domain.model.User

/**
 * Преобразует [UserDto] сетевого слоя в доменную модель [User].
 *
 * `avatarUrl` заменяется на пустую строку, если сервер вернул `null`.
 *
 * @return Доменная модель [User].
 */
fun UserDto.toDomain(): User =
    User(
        id = id,
        name = name,
        email = email,
        avatarUrl = avatarUrl.orEmpty(),
    )
