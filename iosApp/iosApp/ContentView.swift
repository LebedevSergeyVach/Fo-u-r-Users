import SwiftUI

/// Корневой SwiftUI-экран приложения (iOS).
///
/// Зеркалит Android `HomeScreen`: те же цвета, типографика и варианты кнопок, собранные
/// из общих токенов `shared`. По мере появления фич экран подпишется на общую
/// `ViewModel` из `Shared` через мост SKIE (`StateFlow<UiState>` → состояние SwiftUI).
struct ContentView: View {

    @Environment(\.colorScheme) private var colorScheme

    private var theme: AppTheme { AppTheme.of(colorScheme) }

    var body: some View {
        ZStack {
            theme.colorSystem.background
                .ignoresSafeArea()

            VStack(spacing: 12) {
                Text("FourUsers")
                    .appTextStyle(theme.typography.titleLarge)
                    .foregroundStyle(theme.colorSystem.onBackground)

                AppButton(title: "Primary", variant: .primary) { }
                AppButton(title: "Secondary", variant: .secondary) { }
                AppButton(title: "Text", variant: .text) { }
            }
        }
    }
}

#Preview {
    ContentView()
}
