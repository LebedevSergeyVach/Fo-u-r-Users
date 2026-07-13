package space.users.four.serphantom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * Единственная [Activity] Android-приложения — тонкая оболочка над Compose.
 *
 * Включает режим edge-to-edge и устанавливает корневой composable [App].
 * Вся навигация и бизнес-логика живут в общем KMP-модуле, а не здесь.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

/**
 * Preview корневого экрана [App] для Android Studio.
 */
@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
