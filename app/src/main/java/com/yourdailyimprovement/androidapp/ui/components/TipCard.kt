package com.yourdailyimprovement.androidapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yourdailyimprovement.androidapp.domain.model.ImprovementTip
import com.yourdailyimprovement.androidapp.ui.theme.YourDailyImprovementTheme

/**
 * Reusable card that renders a single [ImprovementTip]. Lives in `ui/components`
 * because it is screen-agnostic and can be reused by any future screen.
 */
@Composable
fun TipCard(
    tip: ImprovementTip,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = tip.title, style = MaterialTheme.typography.titleLarge)
            Text(
                text = tip.description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp),
            )
        }
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
