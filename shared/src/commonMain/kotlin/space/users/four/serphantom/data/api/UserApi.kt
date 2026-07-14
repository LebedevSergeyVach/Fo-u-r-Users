package space.users.four.serphantom.data.api

import space.users.four.serphantom.data.model.dto.UserDto

/**
 * Контракт сетевого API пользователей.
 *
 * Методы возвращают DTO **напрямую** (без обёрток) либо бросают исключение —
 * обработка ошибок сосредоточена в `ApiExecutor` (см. `docs/NETWORKING.md`).
 */
interface UserApi {

    /**
     * Загружает список пользователей.
     *
     * @return Список [UserDto].
     */
    suspend fun getUsers(): List<UserDto>
}
