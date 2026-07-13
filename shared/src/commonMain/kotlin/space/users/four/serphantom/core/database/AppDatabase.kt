package space.users.four.serphantom.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

/**
 * Корневая база данных Room приложения.
 *
 * Пока содержит только временную [PlaceholderEntity] (Room требует минимум одну
 * сущность). Реальные сущности и DAO добавляются сюда по мере развития фич.
 *
 * @see [AppDatabaseConstructor]
 */
@Database(
    entities = [PlaceholderEntity::class],
    version = 1,
    exportSchema = true,
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {

    /** DAO-заглушка; заменяется реальными DAO позже. */
    abstract fun placeholderDao(): PlaceholderDao
}

/**
 * Фабрика реализации [AppDatabase] для KMP.
 *
 * `actual`-реализация генерируется Room/KSP для каждой платформы — вручную писать
 * её не нужно (см. предупреждение `NO_ACTUAL_FOR_EXPECT`, которое подавляется намеренно).
 */
@Suppress("NO_ACTUAL_FOR_EXPECT", "KotlinNoActualForExpectDeclaration")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
