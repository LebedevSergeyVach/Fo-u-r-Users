import SwiftUI
import Shared

/// Семантические цветовые роли приложения в терминах SwiftUI.
///
/// Отражение общего контракта `ColorRoles` из `shared` — те же значения, что использует
/// Android. UI работает с ролями, а не с сырой палитрой.
struct AppColorSystem {
    let primary: Color
    let onPrimary: Color
    let background: Color
    let onBackground: Color
    let surface: Color
    let onSurface: Color
    let error: Color
    let onError: Color

    /// Оборачивает общие ARGB-роли в цвета SwiftUI.
    init(roles: ColorRoles) {
        self.primary = Color(argb: roles.primary)
        self.onPrimary = Color(argb: roles.onPrimary)
        self.background = Color(argb: roles.background)
        self.onBackground = Color(argb: roles.onBackground)
        self.surface = Color(argb: roles.surface)
        self.onSurface = Color(argb: roles.onSurface)
        self.error = Color(argb: roles.error)
        self.onError = Color(argb: roles.onError)
    }
}

/// Стиль текста: шрифт и метрики, применяемые модификаторами SwiftUI.
///
/// SwiftUI-шрифт не несёт межстрочный и межбуквенный интервалы, поэтому они хранятся
/// отдельно и применяются через `appTextStyle(_:)`.
struct AppTextStyle {
    let font: Font
    let lineSpacing: CGFloat
    let tracking: CGFloat

    /// Собирает стиль из общих метрик.
    ///
    /// `lineSpacing` в SwiftUI — это *дополнительный* интервал между строками, тогда как
    /// в токенах задана полная высота строки; поэтому вычитается кегль.
    init(tokens: TextStyleTokens) {
        self.font = .system(
            size: CGFloat(tokens.fontSize),
            weight: Font.Weight(cssWeight: tokens.fontWeight)
        )
        self.lineSpacing = CGFloat(tokens.lineHeight - tokens.fontSize)
        self.tracking = CGFloat(tokens.letterSpacing)
    }
}

private extension Font.Weight {

    /// Переводит насыщенность в терминах CSS (400 — обычный, 500 — средний) в вес SwiftUI.
    init(cssWeight: Int32) {
        switch cssWeight {
        case ..<400: self = .light
        case 400: self = .regular
        case 401...500: self = .medium
        case 501...600: self = .semibold
        default: self = .bold
        }
    }
}

/// Типографическая шкала приложения в терминах SwiftUI.
struct AppTypography {
    let titleLarge: AppTextStyle
    let titleMedium: AppTextStyle
    let bodyLarge: AppTextStyle
    let bodyMedium: AppTextStyle
    let labelLarge: AppTextStyle

    /// Собирает шкалу из общих токенов `AppTypographyTokens`.
    init() {
        let tokens = AppTypographyTokens.shared

        self.titleLarge = AppTextStyle(tokens: tokens.titleLarge)
        self.titleMedium = AppTextStyle(tokens: tokens.titleMedium)
        self.bodyLarge = AppTextStyle(tokens: tokens.bodyLarge)
        self.bodyMedium = AppTextStyle(tokens: tokens.bodyMedium)
        self.labelLarge = AppTextStyle(tokens: tokens.labelLarge)
    }
}

/// Радиусы скругления приложения.
struct AppShapes {
    let extraSmall: CGFloat
    let small: CGFloat
    let medium: CGFloat
    let large: CGFloat
    let extraLarge: CGFloat

    /// Собирает набор форм из общих токенов `AppShapeTokens`.
    init() {
        let tokens = AppShapeTokens.shared

        self.extraSmall = CGFloat(tokens.CORNER_EXTRA_SMALL)
        self.small = CGFloat(tokens.CORNER_SMALL)
        self.medium = CGFloat(tokens.CORNER_MEDIUM)
        self.large = CGFloat(tokens.CORNER_LARGE)
        self.extraLarge = CGFloat(tokens.CORNER_EXTRA_LARGE)
    }
}

/// Размерные токены компонентов.
struct AppSizes {
    let buttonMinHeight: CGFloat
    let buttonIconSize: CGFloat
    let buttonPaddingHorizontal: CGFloat
    let buttonPaddingVertical: CGFloat

    /// Собирает размеры из общих токенов `AppSizeTokens`.
    init() {
        let tokens = AppSizeTokens.shared

        self.buttonMinHeight = CGFloat(tokens.BUTTON_MIN_HEIGHT)
        self.buttonIconSize = CGFloat(tokens.BUTTON_ICON_SIZE)
        self.buttonPaddingHorizontal = CGFloat(tokens.BUTTON_PADDING_HORIZONTAL)
        self.buttonPaddingVertical = CGFloat(tokens.BUTTON_PADDING_VERTICAL)
    }
}

/// Единая точка доступа к стилям приложения на iOS.
///
/// Аналог `object AppTheme` на Android. Собирается под текущую системную схему;
/// значения берутся из общих токенов `shared`, поэтому визуал совпадает с Android.
struct AppTheme {
    let colorSystem: AppColorSystem
    let typography: AppTypography
    let shapes: AppShapes
    let sizes: AppSizes

    /// Собирает тему под светлую или тёмную системную схему.
    ///
    /// - Parameter colorScheme: Текущая схема из `@Environment(\.colorScheme)`.
    /// - Returns: Тема с ролями соответствующей схемы.
    static func of(_ colorScheme: ColorScheme) -> AppTheme {
        let roles = colorScheme == .dark ? AppColorRoles.shared.dark : AppColorRoles.shared.light

        return AppTheme(
            colorSystem: AppColorSystem(roles: roles),
            typography: AppTypography(),
            shapes: AppShapes(),
            sizes: AppSizes()
        )
    }
}

extension View {

    /// Применяет стиль текста дизайн-системы: шрифт, межстрочный и межбуквенный интервалы.
    ///
    /// - Parameter style: Стиль из `AppTheme.typography`.
    func appTextStyle(_ style: AppTextStyle) -> some View {
        self
            .font(style.font)
            .lineSpacing(style.lineSpacing)
            .tracking(style.tracking)
    }
}
