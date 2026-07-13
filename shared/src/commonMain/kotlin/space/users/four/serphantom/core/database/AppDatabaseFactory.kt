package space.users.four.serphantom.core.database

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

/**
 * Завершает сборку [AppDatabase] из платформенного [builder].
 *
 * Единая для всех платформ точка: подключает мультиплатформенный драйвер
 * [BundledSQLiteDriver] и собирает базу. Платформенный [builder] создаётся
 * функцией `appDatabaseBuilder(...)` в `androidMain`/`iosMain`.
 *
 * @param [builder] Платформенный билдер [AppDatabase].
 * @return Готовый экземпляр [AppDatabase].
 */
fun createAppDatabase(builder: RoomDatabase.Builder<AppDatabase>): AppDatabase =
    builder
        .setDriver(BundledSQLiteDriver())
        .build()
