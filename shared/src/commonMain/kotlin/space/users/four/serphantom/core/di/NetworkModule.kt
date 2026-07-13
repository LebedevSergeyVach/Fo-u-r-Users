package space.users.four.serphantom.core.di

import io.ktor.client.HttpClient
import org.koin.dsl.module
import space.users.four.serphantom.core.network.ApiExecutor
import space.users.four.serphantom.core.network.HttpClientFactory

/**
 * DI-модуль сетевой инфраструктуры.
 *
 * По мере появления API-классов (`UserApi`, `AuthApi`, ...) их регистрации
 * добавляются сюда: `single<UserApi> { UserApiImpl(httpClient = get()) }`.
 */
val networkModule =
    module {
        single<HttpClient> { HttpClientFactory.create() }
        single<ApiExecutor> { ApiExecutor() }
    }
