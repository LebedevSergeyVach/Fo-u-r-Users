package space.users.four.serphantom.core.di

import org.koin.dsl.module
import space.users.four.serphantom.data.repository.UserRepositoryImpl
import space.users.four.serphantom.domain.repository.UserRepository

/**
 * DI-модуль реализаций репозиториев.
 *
 * Репозитории регистрируются по интерфейсу из `domain`.
 */
val repositoryModule =
    module {
        single<UserRepository> { UserRepositoryImpl(userApi = get(), apiExecutor = get()) }
    }
