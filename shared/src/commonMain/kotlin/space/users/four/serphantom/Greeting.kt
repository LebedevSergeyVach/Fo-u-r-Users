package space.users.four.serphantom

/**
 * Формирует приветственное сообщение с информацией о текущей платформе.
 *
 * Демонстрационная точка проверки работы KMP-модуля на Android и iOS.
 */
class Greeting {

    private val platform = getPlatform()

    /**
     * Возвращает приветствие с именем текущей платформы [Platform.name].
     *
     * @return Строка вида `"Hello, Android 33!"`.
     */
    fun greet(): String = sayHello(platform.name)
}
