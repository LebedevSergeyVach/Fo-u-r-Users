package space.users.four.serphantom.core.di

import org.koin.dsl.module

/**
 * Корневой Koin-модуль — агрегирует все модули приложения через `includes`.
 *
 * Единая точка сборки графа зависимостей: [networkModule], [repositoryModule],
 * [useCaseModule], [viewModelModule]. Новые модули (например, `firebaseModule`)
 * добавляются сюда.
 */
val appModule =
    module {
        includes(
            networkModule,
            repositoryModule,
            useCaseModule,
            viewModelModule,
        )
    }
