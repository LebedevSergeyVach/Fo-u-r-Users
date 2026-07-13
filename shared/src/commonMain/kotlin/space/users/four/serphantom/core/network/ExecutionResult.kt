package space.users.four.serphantom.core.network

/**
 * Единый тип результата выполнения API-запроса.
 *
 * Возвращается из [ApiExecutor.execute] и прокидывается через Repository во ViewModel.
 * Заменяет привычный `try/catch` в бизнес-слоях: обработка ошибок сосредоточена
 * в [ApiExecutor], а потребитель работает с исчерпывающим `when (result)`.
 */
sealed interface ExecutionResult<out T> {
    /** Успешный результат с полезной нагрузкой. */
    data class Success<out T>(
        val data: T,
    ) : ExecutionResult<T>

    /**
     * Ошибка выполнения запроса.
     *
     * @param code HTTP-код ответа (для сетевых ошибок и таймаутов — `null`).
     * @param message Человеко-читаемое сообщение для UI/логов.
     * @param throwable Оригинальное исключение для диагностики и логирования.
     */
    data class Error(
        val code: Int? = null,
        val message: String? = null,
        val throwable: Throwable? = null,
    ) : ExecutionResult<Nothing>
}

/** `true`, если результат — [ExecutionResult.Success]. */
val ExecutionResult<*>.isSuccess: Boolean
    get() = this is ExecutionResult.Success

/** `true`, если результат — [ExecutionResult.Error]. */
val ExecutionResult<*>.isError: Boolean
    get() = this is ExecutionResult.Error

/** Возвращает данные при успехе или `null` при ошибке. */
fun <T> ExecutionResult<T>.getOrNull(): T? = (this as? ExecutionResult.Success)?.data

/** Возвращает данные при успехе или [default] при ошибке. */
fun <T> ExecutionResult<T>.getOrDefault(default: T): T = getOrNull() ?: default

/**
 * Трансформирует данные [ExecutionResult.Success] через [transform],
 * сохраняя [ExecutionResult.Error] без изменений. Основной инструмент
 * маппинга DTO → Domain внутри Repository.
 */
inline fun <T, R> ExecutionResult<T>.map(transform: (T) -> R): ExecutionResult<R> =
    when (this) {
        is ExecutionResult.Success -> ExecutionResult.Success(transform(data))
        is ExecutionResult.Error -> this
    }

/** Выполняет [action] при успехе; возвращает исходный результат для цепочек вызовов. */
inline fun <T> ExecutionResult<T>.onSuccess(action: (T) -> Unit): ExecutionResult<T> {
    if (this is ExecutionResult.Success) action(data)
    return this
}

/** Выполняет [action] при ошибке; возвращает исходный результат для цепочек вызовов. */
inline fun <T> ExecutionResult<T>.onError(action: (ExecutionResult.Error) -> Unit): ExecutionResult<T> {
    if (this is ExecutionResult.Error) action(this)
    return this
}
