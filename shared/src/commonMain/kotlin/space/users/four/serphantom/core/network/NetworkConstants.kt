package space.users.four.serphantom.core.network

/** Базовые сетевые константы: адрес API, таймауты и параметры повторов. */
object NetworkConstants {
    /** Базовый URL API. Все относительные пути [ApiRoutes] резолвятся от него. */
    const val BASE_URL: String = "https://api.fourusers.space/"

    /** Общий таймаут запроса (мс). */
    const val REQUEST_TIMEOUT_MS: Long = 30_000L

    /** Таймаут установки соединения (мс). */
    const val CONNECT_TIMEOUT_MS: Long = 15_000L

    /** Таймаут ожидания данных сокета (мс). */
    const val SOCKET_TIMEOUT_MS: Long = 30_000L

    /** Максимальное число повторов при ошибках сервера (5xx). */
    const val MAX_RETRIES: Int = 3
}
