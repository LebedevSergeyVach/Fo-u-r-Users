package space.users.four.serphantom

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Корневой composable приложения.
 *
 * Единая точка входа UI, общая для Android и iOS. Пока содержит только [MaterialTheme]
 * с пустой поверхностью — экраны и навигация подключаются по мере появления фич
 * (см. `NAVIGATION.md`).
 */
@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
        }
    }
}
