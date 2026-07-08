package com.yourdailyimprovement.androidapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.yourdailyimprovement.androidapp.ui.theme.YourDailyImprovementTheme
import com.yourdailyimprovement.androidapp.ui.theme.spacing

/**
 * Reusable placeholder for "nothing here" / error / first-run screens. The
 * [icon] and [action] are slots, so callers supply their own illustration and
 * button without this component depending on any icon library.
 */
@Composable
fun EmptyState(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    icon: (@Composable () -> Unit)? = null,
    action: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (icon != null) {
            icon()
            Spacer(Modifier.height(MaterialTheme.spacing.sm))
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
        )
        if (description != null) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = MaterialTheme.spacing.xs),
            )
        }
        if (action != null) {
            Column(modifier = Modifier.padding(top = MaterialTheme.spacing.md)) {
                action()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyStatePreview() {
    YourDailyImprovementTheme {
        EmptyState(
            title = "Nothing here yet",
            description = "Your improvement tips will show up here.",
            action = { AppButton(text = "Refresh", onClick = {}) },
        )
    }
}
