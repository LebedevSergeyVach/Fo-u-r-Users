package space.users.four.serphantom.core.di

import org.koin.dsl.module

/**
 * DI-модуль UseCase (интеракторов).
 *
 * UseCase регистрируются как `single` (синглтоны): могут кэшировать данные и хранить
 * общее состояние, переиспользуются несколькими ViewModel. Пока фич нет — модуль пуст.
 */
val useCaseModule =
    module {
    }
