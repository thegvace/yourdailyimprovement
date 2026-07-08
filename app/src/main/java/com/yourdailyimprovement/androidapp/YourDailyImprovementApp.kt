package com.yourdailyimprovement.androidapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application entry point. [HiltAndroidApp] triggers Hilt's code generation and
 * creates the application-level dependency container.
 */
@HiltAndroidApp
class YourDailyImprovementApp : Application()
