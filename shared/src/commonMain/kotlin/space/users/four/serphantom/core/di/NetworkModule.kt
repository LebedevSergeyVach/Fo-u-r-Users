package space.users.four.serphantom.core.di

import io.ktor.client.HttpClient
import org.koin.dsl.module
import space.users.four.serphantom.core.network.ApiExecutor
import space.users.four.serphantom.core.network.HttpClientFactory
import space.users.four.serphantom.data.api.UserApi
import space.users.four.serphantom.data.api.UserApiImpl

/**
 * DI-модуль сетевой инфраструктуры и API-классов.
 *
 * Новые API-классы (`AuthApi`, ...) регистрируются здесь по интерфейсу:
 * `single<AuthApi> { AuthApiImpl(httpClient = get()) }`.
 */
val networkModule =
    module {
        single<HttpClient> { HttpClientFactory.create() }
        single<ApiExecutor> { ApiExecutor() }
        single<UserApi> { UserApiImpl(httpClient = get()) }
    }
