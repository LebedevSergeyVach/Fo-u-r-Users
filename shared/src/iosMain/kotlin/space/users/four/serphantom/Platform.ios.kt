package space.users.four.serphantom

import platform.UIKit.UIDevice

/**
 * iOS-реализация [Platform].
 *
 * Название формируется из данных [UIDevice] — системного имени и версии ОС.
 */
class IOSPlatform : Platform {

    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

/**
 * Возвращает [IOSPlatform] как реализацию [Platform] на iOS.
 *
 * @return Экземпляр [IOSPlatform].
 */
actual fun getPlatform(): Platform = IOSPlatform()
