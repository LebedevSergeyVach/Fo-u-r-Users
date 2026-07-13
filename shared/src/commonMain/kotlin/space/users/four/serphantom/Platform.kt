package space.users.four.serphantom

/**
 * Абстракция платформы, на которой выполняется приложение.
 *
 * Реализуется механизмом `expect`/`actual` отдельно для Android и iOS.
 */
interface Platform {

    /** Человеко-читаемое название и версия платформы. */
    val name: String
}

/**
 * Возвращает реализацию [Platform] для текущей платформы.
 *
 * @return [Platform] соответствующей платформы (Android или iOS).
 */
expect fun getPlatform(): Platform
