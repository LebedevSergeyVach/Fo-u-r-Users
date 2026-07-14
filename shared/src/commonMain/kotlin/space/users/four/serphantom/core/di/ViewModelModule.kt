package space.users.four.serphantom.core.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import space.users.four.serphantom.presentation.users.UsersViewModel

/**
 * DI-модуль ViewModel-фабрик.
 *
 * ViewModel получают в конструктор только UseCase (не Repository).
 */
val viewModelModule =
    module {
        viewModelOf(::UsersViewModel)
    }
