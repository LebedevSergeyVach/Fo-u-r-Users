package space.users.four.serphantom.core.di

import io.ktor.client.HttpClient
import org.koin.dsl.module
import space.users.four.serphantom.core.network.ApiExecutor
import space.users.four.serphantom.core.network.HttpClientFactory

/**
 * DI-модуль сетевой инфраструктуры и API-классов.
 *
 * Здесь регистрируются общий [HttpClient] и [ApiExecutor]. Конкретные API-классы
 * добавляются по интерфейсу по мере появления фич:
 * `single<AuthApi> { AuthApiImpl(httpClient = get()) }`.
 */
val networkModule =
    module {
        single<HttpClient> { HttpClientFactory.create() }
        single<ApiExecutor> { ApiExecutor() }
    }
