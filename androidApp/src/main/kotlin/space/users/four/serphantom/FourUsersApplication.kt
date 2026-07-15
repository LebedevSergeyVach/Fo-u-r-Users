package space.users.four.serphantom

import android.app.Application
import space.users.four.serphantom.core.di.initKoin

/**
 * [Application] Android-приложения.
 *
 * Инициализирует Koin один раз при старте процесса — до создания [MainActivity]
 * и любого обращения к графу зависимостей из UI.
 */
class FourUsersApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
