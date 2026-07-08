package com.yourdailyimprovement.androidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.yourdailyimprovement.androidapp.navigation.AppNavHost
import com.yourdailyimprovement.androidapp.ui.theme.YourDailyImprovementTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Single-activity host. [AndroidEntryPoint] lets Hilt inject dependencies into
 * composables (via ViewModels). Each screen owns its own Scaffold/top bar, so
 * this only sets the theme, a background surface, and the navigation graph.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YourDailyImprovementTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    AppNavHost()
                }
            }
        }
    }
}
