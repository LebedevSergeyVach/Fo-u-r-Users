package space.users.four.serphantom.core.network

/**
 * Относительные пути эндпоинтов API.
 *
 * Резолвятся относительно [NetworkConstants.BASE_URL] через `defaultRequest`,
 * поэтому задаются без ведущего слэша и без хоста.
 */
object ApiRoutes {

    /** Коллекция пользователей: `GET /users`, `POST /users`. */
    const val USERS: String = "users"
}
