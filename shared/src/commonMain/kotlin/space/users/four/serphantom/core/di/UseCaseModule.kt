package space.users.four.serphantom.core.di

import org.koin.dsl.module
import space.users.four.serphantom.domain.usecase.GetUsersUseCase

/**
 * DI-модуль UseCase (интеракторов).
 *
 * UseCase регистрируются как `single` (синглтоны): могут кэшировать данные и хранить
 * общее состояние, переиспользуются несколькими ViewModel.
 */
val useCaseModule =
    module {
        single { GetUsersUseCase(userRepository = get()) }
    }
