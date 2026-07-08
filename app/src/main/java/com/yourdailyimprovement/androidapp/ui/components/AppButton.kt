package com.yourdailyimprovement.androidapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yourdailyimprovement.androidapp.ui.theme.YourDailyImprovementTheme

enum class AppButtonVariant { Primary, Secondary }

/**
 * The app's single button entry point. Screens use this instead of Material's
 * [Button]/[OutlinedButton] directly so every button shares the same height,
 * shape and variant behaviour.
 */
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: AppButtonVariant = AppButtonVariant.Primary,
    enabled: Boolean = true,
    fillWidth: Boolean = false,
) {
    val sizing = Modifier
        .then(if (fillWidth) Modifier.fillMaxWidth() else Modifier)
        .heightIn(min = 48.dp)

    when (variant) {
        AppButtonVariant.Primary -> Button(
            onClick = onClick,
            modifier = modifier.then(sizing),
            enabled = enabled,
        ) {
            Text(text)
        }

        AppButtonVariant.Secondary -> OutlinedButton(
            onClick = onClick,
            modifier = modifier.then(sizing),
            enabled = enabled,
        ) {
            Text(text)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppButtonPreview() {
    YourDailyImprovementTheme {
        AppButton(text = "Primary action", onClick = {})
    }
}
