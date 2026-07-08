package com.yourdailyimprovement.androidapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yourdailyimprovement.androidapp.domain.model.ImprovementTip
import com.yourdailyimprovement.androidapp.ui.theme.YourDailyImprovementTheme

/**
 * Stateful entry point: obtains the [HomeViewModel] from Hilt and observes its
 * state in a lifecycle-aware way, then delegates rendering to [HomeContent].
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeContent(
        uiState = uiState,
        onRetry = viewModel::loadTip,
        modifier = modifier,
    )
}

/**
 * Stateless UI. Takes plain state + callbacks so it is trivial to preview and
 * test without Hilt or a ViewModel.
 */
@Composable
fun HomeContent(
    uiState: HomeUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Your Daily Improvement",
            style = MaterialTheme.typography.headlineMedium,
        )

        when (uiState) {
            HomeUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.padding(top = 24.dp).size(48.dp))
            }

            is HomeUiState.Success -> {
                TipCard(tip = uiState.tip, modifier = Modifier.padding(top = 24.dp))
            }

            is HomeUiState.Error -> {
                Text(
                    text = uiState.message,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 24.dp),
                )
                Button(onClick = onRetry, modifier = Modifier.padding(top = 16.dp)) {
                    Text("Retry")
                }
            }
        }
    }
}

@Composable
private fun TipCard(tip: ImprovementTip, modifier: Modifier = Modifier) {
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
private fun HomeContentSuccessPreview() {
    YourDailyImprovementTheme {
        HomeContent(
            uiState = HomeUiState.Success(
                ImprovementTip(1, "Start small", "Pick one tiny habit and do it today."),
            ),
            onRetry = {},
        )
    }
}
