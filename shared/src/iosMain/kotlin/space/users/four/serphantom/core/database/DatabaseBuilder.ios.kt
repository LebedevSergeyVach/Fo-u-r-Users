package space.users.four.serphantom.core.database

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

/**
 * Создаёт платформенный билдер [AppDatabase] для iOS.
 *
 * Файл БД размещается в каталоге Documents приложения. Готовый билдер
 * передаётся в [createAppDatabase] для финальной сборки.
 *
 * @return Билдер [AppDatabase], привязанный к файлу [DatabaseConstants.DATABASE_NAME].
 */
fun appDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val databasePath = documentDirectory() + "/" + DatabaseConstants.DATABASE_NAME
    return Room.databaseBuilder<AppDatabase>(name = databasePath)
}

/**
 * Возвращает путь к каталогу Documents текущего приложения.
 *
 * @return Абсолютный путь к каталогу `NSDocumentDirectory`.
 */
@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory: NSURL? =
        NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
    return requireNotNull(documentDirectory?.path)
}
