package space.users.four.serphantom.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Временная сущность-заглушка.
 *
 * Существует только для того, чтобы [AppDatabase] был валидным (Room требует
 * минимум одну `@Entity`) и чтобы отработала кодогенерация KSP. Удаляется/заменяется,
 * как только появятся реальные сущности данных.
 *
 * @param [id] Первичный ключ строки.
 */
@Entity(tableName = "placeholder")
data class PlaceholderEntity(
    @PrimaryKey val id: Long = 0,
)
