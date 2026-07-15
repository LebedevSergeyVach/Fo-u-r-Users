package space.users.four.serphantom.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Заглушка стартового экрана.
 *
 * Базовый каркас: подтверждает, что тема и навигация подключены. Наполняется
 * реальным UI и подключается к ViewModel через `koinViewModel()` по мере появления фич.
 */
@Composable
fun HomeScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "FourUsers")
        }
    }
}
