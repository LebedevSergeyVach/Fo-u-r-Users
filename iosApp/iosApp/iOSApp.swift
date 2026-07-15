import SwiftUI
import Shared

@main
struct iOSApp: App {

    /// Инициализирует Koin один раз при старте процесса — до построения UI.
    init() {
        KoinInitIosKt.initKoinIos()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
