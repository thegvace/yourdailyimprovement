package com.yourdailyimprovement.androidapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yourdailyimprovement.androidapp.ui.home.HomeScreen

/**
 * Single source of truth for the app's navigation graph. New screens are added
 * here as additional [composable] destinations. Milestone 1 has a single Home
 * destination; navigation is wired so later milestones just add routes.
 */
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.HOME_ROUTE,
        modifier = modifier,
    ) {
        composable(Destinations.HOME_ROUTE) {
            HomeScreen()
        }
    }
}
