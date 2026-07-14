package space.users.four.serphantom.core.result

/**
 * Единый тип результата выполнения операции (сеть, БД, бизнес-логика).
 *
 * Нейтральный shared-kernel: не зависит от Ktor/Room и любого фреймворка, поэтому
 * его безопасно возвращают контракты `domain` (Repository, UseCase). Обработка ошибок
 * сосредоточена в `ApiExecutor`, а потребитель работает с исчерпывающим `when (result)`.
 */
sealed interface ExecutionResult<out T> {

    /**
     * Успешный результат с полезной нагрузкой.
     *
     * @param [data] Данные, полученные в результате операции.
     */
    data class Success<out T>(
        val data: T,
    ) : ExecutionResult<T>

    /**
     * Ошибка выполнения операции.
     *
     * @param [code] HTTP-код ответа; для сетевых ошибок и таймаутов — `null`.
     * @param [message] Человеко-читаемое сообщение для UI и логов.
     * @param [throwable] Оригинальное исключение для диагностики и логирования.
     */
    data class Error(
        val code: Int? = null,
        val message: String? = null,
        val throwable: Throwable? = null,
    ) : ExecutionResult<Nothing>
}

/**
 * Признак успешного результата.
 *
 * @return `true`, если результат — [ExecutionResult.Success].
 */
val ExecutionResult<*>.isSuccess: Boolean
    get() = this is ExecutionResult.Success

/**
 * Признак ошибки.
 *
 * @return `true`, если результат — [ExecutionResult.Error].
 */
val ExecutionResult<*>.isError: Boolean
    get() = this is ExecutionResult.Error

/**
 * Извлекает данные результата или `null`.
 *
 * @return Данные при успехе или `null` при ошибке.
 */
fun <T> ExecutionResult<T>.getOrNull(): T? = (this as? ExecutionResult.Success)?.data

/**
 * Извлекает данные результата или значение по умолчанию.
 *
 * @param [default] Значение, возвращаемое при ошибке.
 * @return Данные при успехе или [default] при ошибке.
 */
fun <T> ExecutionResult<T>.getOrDefault(default: T): T = getOrNull() ?: default

/**
 * Трансформирует данные [ExecutionResult.Success] через [transform],
 * сохраняя [ExecutionResult.Error] без изменений.
 *
 * Основной инструмент маппинга DTO → Domain внутри Repository.
 *
 * @param [transform] Функция преобразования данных при успехе.
 * @return Новый [ExecutionResult] с преобразованными данными или исходная ошибка.
 */
inline fun <T, R> ExecutionResult<T>.map(transform: (T) -> R): ExecutionResult<R> =
    when (this) {
        is ExecutionResult.Success -> ExecutionResult.Success(transform(data))
        is ExecutionResult.Error -> this
    }

/**
 * Выполняет [action] при успешном результате.
 *
 * @param [action] Действие над данными при успехе.
 * @return Исходный результат — для построения цепочек вызовов.
 */
inline fun <T> ExecutionResult<T>.onSuccess(action: (T) -> Unit): ExecutionResult<T> {
    if (this is ExecutionResult.Success) action(data)
    return this
}

/**
 * Выполняет [action] при ошибке.
 *
 * @param [action] Действие над [ExecutionResult.Error] при ошибке.
 * @return Исходный результат — для построения цепочек вызовов.
 */
inline fun <T> ExecutionResult<T>.onError(action: (ExecutionResult.Error) -> Unit): ExecutionResult<T> {
    if (this is ExecutionResult.Error) action(this)
    return this
}
