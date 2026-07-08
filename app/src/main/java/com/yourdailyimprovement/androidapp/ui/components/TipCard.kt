package com.yourdailyimprovement.androidapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yourdailyimprovement.androidapp.domain.model.ImprovementTip
import com.yourdailyimprovement.androidapp.ui.theme.YourDailyImprovementTheme
import com.yourdailyimprovement.androidapp.ui.theme.spacing

/**
 * Renders a single [ImprovementTip] on the shared [AppCard] surface. Because it
 * builds on [AppCard], it automatically matches every other card/list item in
 * the app.
 */
@Composable
fun TipCard(
    tip: ImprovementTip,
    modifier: Modifier = Modifier,
) {
    AppCard(modifier = modifier) {
        Text(
            text = tip.title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = tip.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = MaterialTheme.spacing.sm),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TipCardPreview() {
    YourDailyImprovementTheme {
        TipCard(
            tip = ImprovementTip(1, "Start small", "Pick one tiny habit and do it today."),
            modifier = Modifier.padding(16.dp),
        )
    }
}
