package space.users.four.serphantom.core.di

/**
 * Инициализирует Koin на iOS — обёртка над [initKoin] без параметров.
 *
 * Вызывается из Swift-точки входа (`iOSApp.init`), где неудобно передавать
 * Kotlin-лямбду с ресивером. В Swift доступна как `KoinInitIosKt.initKoinIos()`.
 */
fun initKoinIos() {
    initKoin()
}
