import SwiftUI

extension Color {

    /// Создаёт цвет из ARGB-токена общего слоя (`0xAARRGGBB`).
    ///
    /// Токены хранятся в `shared` как `Long`, поэтому в Swift приходят как `Int64`.
    /// Это единственное место, где значение цвета разбирается на компоненты — весь
    /// остальной iOS-код работает с готовыми ролями `AppColorSystem`.
    ///
    /// - Parameter argb: Упакованное значение цвета в формате `0xAARRGGBB`.
    init(argb: Int64) {
        let alpha = Double((argb >> 24) & 0xFF) / 255.0
        let red = Double((argb >> 16) & 0xFF) / 255.0
        let green = Double((argb >> 8) & 0xFF) / 255.0
        let blue = Double(argb & 0xFF) / 255.0

        self.init(.sRGB, red: red, green: green, blue: blue, opacity: alpha)
    }
}
