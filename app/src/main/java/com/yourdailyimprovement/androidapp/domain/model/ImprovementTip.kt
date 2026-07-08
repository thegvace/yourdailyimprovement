package com.yourdailyimprovement.androidapp.domain.model

/**
 * A single daily self-improvement tip. Pure domain model — no framework or
 * data-source concerns.
 */
data class ImprovementTip(
    val id: Int,
    val title: String,
    val description: String,
)
