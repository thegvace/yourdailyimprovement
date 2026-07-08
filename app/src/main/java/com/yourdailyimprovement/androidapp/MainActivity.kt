package com.yourdailyimprovement.androidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.yourdailyimprovement.androidapp.navigation.AppNavHost
import com.yourdailyimprovement.androidapp.ui.theme.YourDailyImprovementTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Single-activity host. [AndroidEntryPoint] lets Hilt inject dependencies into
 * composables (via ViewModels) hosted here. All screens live inside [AppNavHost].
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YourDailyImprovementTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
