package space.users.four.serphantom.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import space.users.four.serphantom.presentation.common.components.AppButton
import space.users.four.serphantom.presentation.common.theme.AppTheme

/**
 * Заглушка стартового экрана.
 *
 * Базовый каркас: подтверждает, что тема и навигация подключены. Все цвета, текстовые
 * стили и стили кнопок берутся из токенов темы ([AppTheme]), а не задаются вручную.
 * Наполняется реальным UI и подключается к ViewModel через `koinViewModel()` по мере
 * появления фич.
 */
@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppTheme.colorSystem.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 12.dp, alignment = Alignment.CenterVertically),
        ) {
            Text(
                text = "FourUsers",
                color = AppTheme.colorSystem.onBackground,
                style = AppTheme.typography.titleLarge,
            )
            AppButton(
                text = "Primary",
                onClick = { },
                style = AppTheme.buttons.primary,
            )
            AppButton(
                text = "Secondary",
                onClick = { },
                style = AppTheme.buttons.secondary,
            )
            AppButton(
                text = "Text",
                onClick = { },
                style = AppTheme.buttons.text,
            )
        }
    }
}
