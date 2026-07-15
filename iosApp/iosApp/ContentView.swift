import SwiftUI

/// Корневой SwiftUI-экран приложения (iOS).
///
/// Пока заглушка-каркас, зеркалит Android `HomeScreen`. По мере появления фич
/// экран подписывается на общую `ViewModel` из модуля `Shared` через мост SKIE
/// (`StateFlow<UiState>` → наблюдаемое состояние SwiftUI).
struct ContentView: View {
    var body: some View {
        Text("FourUsers")
    }
}
