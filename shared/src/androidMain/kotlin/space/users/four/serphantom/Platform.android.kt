package space.users.four.serphantom

import android.os.Build

/**
 * Android-реализация [Platform].
 *
 * Название формируется из версии SDK устройства [Build.VERSION.SDK_INT].
 */
class AndroidPlatform : Platform {

    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

/**
 * Возвращает [AndroidPlatform] как реализацию [Platform] на Android.
 *
 * @return Экземпляр [AndroidPlatform].
 */
actual fun getPlatform(): Platform = AndroidPlatform()
