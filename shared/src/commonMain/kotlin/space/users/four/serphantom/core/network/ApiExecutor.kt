package space.users.four.serphantom.core.network

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.CancellationException

/**
 * Единственная точка обработки ошибок сетевых вызовов в приложении.
 *
 * Repository оборачивает вызов API в [execute], а результат возвращается как
 * [ExecutionResult]. Все исключения Ktor перехватываются здесь — `try/catch`
 * в других слоях запрещён (см. `docs/NETWORKING.md`).
 *
 * Требует, чтобы у [io.ktor.client.HttpClient] был выставлен `expectSuccess = true`
 * (см. [HttpClientFactory]) — иначе Ktor не бросает исключения на 4xx/5xx.
 */
class ApiExecutor {

    /**
     * Безопасно выполняет [block] и оборачивает результат в [ExecutionResult].
     *
     * [CancellationException] пробрасывается наружу без перехвата — иначе отмена
     * корутины (например, при уходе с экрана) будет молча проглочена и превратится
     * в ложную ошибку.
     *
     * @param [block] Сетевой вызов API, возвращающий DTO напрямую.
     * @return [ExecutionResult.Success] с результатом [block] либо
     *   [ExecutionResult.Error] с описанием перехваченной ошибки.
     * @throws CancellationException Пробрасывается наружу при отмене корутины.
     */
    suspend fun <T> execute(block: suspend () -> T): ExecutionResult<T> =
        try {
            ExecutionResult.Success(block())
        } catch (cancellation: CancellationException) {
            throw cancellation
        } catch (exception: ClientRequestException) {
            // 4xx — ошибки клиента (400, 401, 403, 404, ...)
            ExecutionResult.Error(
                code = exception.response.status.value,
                message = "Ошибка клиента: ${exception.response.status.description}",
                throwable = exception,
            )
        } catch (exception: ServerResponseException) {
            // 5xx — ошибки сервера (500, 502, 503, ...)
            ExecutionResult.Error(
                code = exception.response.status.value,
                message = "Ошибка сервера: ${exception.response.status.description}",
                throwable = exception,
            )
        } catch (exception: RedirectResponseException) {
            // 3xx — необработанные перенаправления
            ExecutionResult.Error(
                code = exception.response.status.value,
                message = "Перенаправление: ${exception.response.status.description}",
                throwable = exception,
            )
        } catch (exception: HttpRequestTimeoutException) {
            // Превышен таймаут запроса
            ExecutionResult.Error(
                code = null,
                message = "Превышено время ожидания запроса",
                throwable = exception,
            )
        } catch (exception: Exception) {
            // Нет сети, DNS, разрыв соединения, ошибка сериализации и прочее
            ExecutionResult.Error(
                code = null,
                message = exception.message ?: "Неизвестная ошибка сети",
                throwable = exception,
            )
        }
}
