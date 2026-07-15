package space.users.four.serphantom.core.di

import org.koin.dsl.module

/**
 * DI-модуль реализаций репозиториев.
 *
 * Репозитории регистрируются по интерфейсу из `domain`:
 * `single<UserRepository> { UserRepositoryImpl(userApi = get(), apiExecutor = get()) }`.
 * Пока фич нет — модуль пуст.
 */
val repositoryModule =
    module {
    }
