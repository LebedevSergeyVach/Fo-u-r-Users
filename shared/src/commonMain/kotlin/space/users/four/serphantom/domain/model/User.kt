package space.users.four.serphantom.domain.model

/**
 * Доменная модель пользователя.
 *
 * Чистая бизнес-модель без зависимостей от фреймворков и без nullable-полей
 * (маппер обрабатывает отсутствующие значения из DTO).
 *
 * @param [id] Уникальный идентификатор пользователя.
 * @param [name] Отображаемое имя.
 * @param [email] Электронная почта.
 * @param [avatarUrl] URL аватара; пустая строка, если не задан.
 */
data class User(
    val id: String,
    val name: String,
    val email: String,
    val avatarUrl: String,
)
