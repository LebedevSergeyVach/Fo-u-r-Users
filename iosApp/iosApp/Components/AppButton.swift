import SwiftUI
import Shared

/// Вариант кнопки из дизайн-системы.
///
/// Зеркалит `AppButtons` на Android: основное, второстепенное и третьестепенное действие.
enum AppButtonVariant {
    /// Основное действие — заливка акцентом.
    case primary
    /// Второстепенное действие — тональная заливка.
    case secondary
    /// Третьестепенное действие — без заливки.
    case text
}

/// Кнопка приложения — отрисовка по токенам дизайн-системы.
///
/// Все параметры внешнего вида берутся из общих токенов `shared`, поэтому вызывающему
/// коду не нужно (и не следует) задавать цвета, отступы или скругление вручную.
struct AppButton: View {
    let title: String
    var variant: AppButtonVariant = .primary
    var isEnabled: Bool = true
    let action: () -> Void

    @Environment(\.colorScheme) private var colorScheme

    private var theme: AppTheme { AppTheme.of(colorScheme) }

    var body: some View {
        Button(action: action) {
            Text(title)
                .appTextStyle(theme.typography.labelLarge)
                .padding(.horizontal, theme.sizes.buttonPaddingHorizontal)
                .padding(.vertical, theme.sizes.buttonPaddingVertical)
                .frame(minHeight: theme.sizes.buttonMinHeight)
                .foregroundStyle(contentColor)
                .background(containerColor)
                .clipShape(RoundedRectangle(cornerRadius: theme.shapes.small))
        }
        .buttonStyle(.plain)
        .disabled(!isEnabled)
    }

    /// Цвет заливки с учётом варианта и доступности.
    private var containerColor: Color {
        guard isEnabled else {
            return variant == .text
                ? .clear
                : theme.colorSystem.onSurface.opacity(Double(AppAlphaTokens.shared.DISABLED_CONTAINER))
        }

        switch variant {
        case .primary:
            return theme.colorSystem.primary
        case .secondary:
            return theme.colorSystem.primary.opacity(Double(AppAlphaTokens.shared.TONAL_CONTAINER))
        case .text:
            return .clear
        }
    }

    /// Цвет содержимого с учётом варианта и доступности.
    private var contentColor: Color {
        guard isEnabled else {
            return theme.colorSystem.onSurface.opacity(Double(AppAlphaTokens.shared.DISABLED_CONTENT))
        }

        switch variant {
        case .primary:
            return theme.colorSystem.onPrimary
        case .secondary, .text:
            return theme.colorSystem.primary
        }
    }
}
