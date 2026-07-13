package space.users.four.serphantom

/**
 * Возвращает приветственную строку для переданного адресата.
 *
 * @param [to] Имя или название платформы, к которой обращено приветствие.
 * @return Строка вида `"Hello, <to>!"`.
 */
fun sayHello(to: String): String = "Hello, $to!"
