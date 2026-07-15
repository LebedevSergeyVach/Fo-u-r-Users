package space.users.four.serphantom.core.network

/**
 * Относительные пути эндпоинтов API.
 *
 * Резолвятся относительно [NetworkConstants.BASE_URL] через `defaultRequest`,
 * поэтому задаются без ведущего слэша и без хоста. Наполняется по мере появления
 * эндпоинтов, например: `const val AUTH: String = "auth"`.
 */
object ApiRoutes
