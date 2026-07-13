package space.users.four.serphantom

import androidx.compose.ui.window.ComposeUIViewController

/**
 * Создаёт корневой iOS-контроллер с Compose-интерфейсом приложения.
 *
 * Оборачивает корневой composable [App] в [ComposeUIViewController], который
 * встраивается в нативный iOS-проект (`iosApp`, `ContentView.swift`). Имя в
 * PascalCase — контракт интеграции: Swift вызывает `MainViewControllerKt.MainViewController()`.
 *
 * @return [ComposeUIViewController] с UI приложения.
 */
@Suppress("ktlint:standard:function-naming")
fun MainViewController() = ComposeUIViewController { App() }
