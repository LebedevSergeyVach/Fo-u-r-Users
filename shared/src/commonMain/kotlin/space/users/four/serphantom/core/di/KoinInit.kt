package space.users.four.serphantom.core.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

/**
 * Инициализирует Koin с корневым модулем [appModule].
 *
 * Вызывается один раз при старте приложения: на Android — из `Application`,
 * на iOS — из Swift-точки входа. [additionalConfig] позволяет платформе добавить
 * свою конфигурацию (например, `androidContext` на Android).
 *
 * @param [additionalConfig] Дополнительная настройка Koin от платформы (опционально).
 * @return Запущенное [KoinApplication].
 */
fun initKoin(additionalConfig: (KoinApplication.() -> Unit)? = null): KoinApplication =
    startKoin {
        additionalConfig?.invoke(this)
        modules(appModule)
    }
