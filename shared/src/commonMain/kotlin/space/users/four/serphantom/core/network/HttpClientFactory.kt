package space.users.four.serphantom.core.network

import io.github.mahmoud.ktorscope.ktor.KtorScope
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Фабрика единственного [HttpClient] приложения.
 *
 * Движок (OkHttp на Android, Darwin на iOS) подхватывается автоматически из
 * зависимостей соответствующего sourceSet — явно указывать его не нужно.
 * Клиент регистрируется как `single` в `NetworkModule`.
 */
object HttpClientFactory {

    /**
     * Создаёт преднастроенный [HttpClient] с настройками проекта.
     *
     * Подключает согласование контента [ContentNegotiation] с [Json], таймауты
     * [HttpTimeout], повтор запросов [HttpRequestRetry], логирование [Logging] и
     * базовые параметры запроса ([NetworkConstants.BASE_URL], `Content-Type`).
     *
     * @return Готовый к использованию [HttpClient].
     */
    fun create(): HttpClient =
        HttpClient {
            // Без этого флага Ktor не бросает ClientRequestException / ServerResponseException
            // на 4xx/5xx, и ApiExecutor не сможет распознать ошибки HTTP.
            expectSuccess = true

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        prettyPrint = false
                        encodeDefaults = true
                        explicitNulls = false
                    },
                )
            }

            install(HttpTimeout) {
                requestTimeoutMillis = NetworkConstants.REQUEST_TIMEOUT_MS
                connectTimeoutMillis = NetworkConstants.CONNECT_TIMEOUT_MS
                socketTimeoutMillis = NetworkConstants.SOCKET_TIMEOUT_MS
            }

            install(HttpRequestRetry) {
                retryOnServerErrors(maxRetries = NetworkConstants.MAX_RETRIES)
                exponentialDelay()
            }

            install(Logging) {
                // Logger.SIMPLE / Logger.DEFAULT доступны только в JVM-sourceSet,
                // поэтому в commonMain используем мультиплатформенный логгер на println.
                logger =
                    object : Logger {
                        override fun log(message: String) {
                            println("[Ktor] $message")
                        }
                    }
                level = LogLevel.BODY
            }

            // KtorScope — захват запросов/ответов для просмотра на устройстве
            // (экран открывается через presentation/debug/NetworkInspectorScreen).
            // Для релиза стоит гейтить enabled по debug-флагу.
            install(KtorScope) {
                enabled = true
                captureBodies = true
                prettyPrint = true
                // WebSockets не используются — кадры не захватываем.
                captureWebSocketFrames = false
                // redactHeaders по умолчанию скрывает Authorization, Cookie,
                // Set-Cookie, X-Api-Key, Api-Key, access_token, refresh_token.
            }

            defaultRequest {
                url(NetworkConstants.BASE_URL)
                contentType(ContentType.Application.Json)
            }
        }
}
