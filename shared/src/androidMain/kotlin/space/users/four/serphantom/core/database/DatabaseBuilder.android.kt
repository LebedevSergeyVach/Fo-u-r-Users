package space.users.four.serphantom.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Создаёт платформенный билдер [AppDatabase] для Android.
 *
 * Файл БД размещается в каталоге баз данных приложения. Готовый билдер
 * передаётся в [createAppDatabase] для финальной сборки.
 *
 * @param [context] Контекст приложения (внедряется через Koin `androidContext`).
 * @return Билдер [AppDatabase], привязанный к файлу [DatabaseConstants.DATABASE_NAME].
 */
fun appDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    val databaseFile = context.getDatabasePath(DatabaseConstants.DATABASE_NAME)
    return Room.databaseBuilder<AppDatabase>(
        context = context.applicationContext,
        name = databaseFile.absolutePath,
    )
}
