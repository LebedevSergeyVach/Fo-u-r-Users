package space.users.four.serphantom.core.navigation

import kotlinx.serialization.Serializable

/**
 * Стартовый экран приложения — type-safe маршрут навигации.
 *
 * Маршруты объявляются как `@Serializable` объекты/классы; параметры передаются
 * полями `data class`. Строковые маршруты не используются (см. `NAVIGATION.md`).
 */
@Serializable
data object HomeRoute
