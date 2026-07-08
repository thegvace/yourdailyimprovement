package com.yourdailyimprovement.androidapp.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yourdailyimprovement.androidapp.domain.model.ImprovementTip
import com.yourdailyimprovement.androidapp.ui.components.AppButton
import com.yourdailyimprovement.androidapp.ui.components.AppButtonVariant
import com.yourdailyimprovement.androidapp.ui.components.EmptyState
import com.yourdailyimprovement.androidapp.ui.components.TipCard
import com.yourdailyimprovement.androidapp.ui.theme.YourDailyImprovementTheme
import com.yourdailyimprovement.androidapp.ui.theme.spacing

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
 * test without Hilt or a ViewModel. Built from the shared design-system
 * components so it matches the rest of the app.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    uiState: HomeUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Your Daily Improvement") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            when (uiState) {
                HomeUiState.Loading -> CircularProgressIndicator()

                is HomeUiState.Success -> TipContent(tip = uiState.tip)

                is HomeUiState.Error -> EmptyState(
                    title = "Couldn't load your tip",
                    description = uiState.message,
                    action = {
                        AppButton(
                            text = "Try again",
                            onClick = onRetry,
                            variant = AppButtonVariant.Primary,
                        )
                    },
                )
            }
        }
    }
}

@Composable
private fun TipContent(tip: ImprovementTip) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(MaterialTheme.spacing.lg),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
    ) {
        Text(
            text = "TODAY'S TIP",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
        )
        TipCard(tip = tip)
    }
}

@Preview(showBackground = true, name = "Home – success")
@Composable
private fun HomeSuccessPreview() {
    YourDailyImprovementTheme {
        HomeContent(
            uiState = HomeUiState.Success(
                ImprovementTip(1, "Move your body", "A short walk clears the mind and resets your focus."),
            ),
            onRetry = {},
        )
    }
}

@Preview(showBackground = true, name = "Home – error")
@Composable
private fun HomeErrorPreview() {
    YourDailyImprovementTheme {
        HomeContent(
            uiState = HomeUiState.Error("No connection"),
            onRetry = {},
        )
    }
}
