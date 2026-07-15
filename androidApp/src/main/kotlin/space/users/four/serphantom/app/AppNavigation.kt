package space.users.four.serphantom.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import space.users.four.serphantom.core.navigation.HomeRoute
import space.users.four.serphantom.presentation.home.HomeScreen

/**
 * Корневой навигационный граф приложения — единственный `NavHost`.
 *
 * Стартовый маршрут — [HomeRoute]. Новые экраны добавляются как `composable<Route>`,
 * навигация передаётся экранам через лямбда-колбеки (см. `NAVIGATION.md`).
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRoute,
    ) {
        composable<HomeRoute> {
            HomeScreen()
        }
    }
}
