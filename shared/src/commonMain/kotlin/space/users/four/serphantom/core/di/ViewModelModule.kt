package space.users.four.serphantom.core.di

import org.koin.dsl.module

/**
 * DI-модуль ViewModel-фабрик.
 *
 * ViewModel получают в конструктор только UseCase (не Repository) и регистрируются
 * через `viewModelOf(::SomeViewModel)`. Пока фич нет — модуль пуст.
 */
val viewModelModule =
    module {
    }
