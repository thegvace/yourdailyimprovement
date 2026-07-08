package com.yourdailyimprovement.androidapp.data.local

import com.yourdailyimprovement.androidapp.domain.model.ImprovementTip
import javax.inject.Inject

/**
 * Local source of improvement tips. Currently an in-memory list; in a later
 * milestone this can be swapped for Room or DataStore without changing the
 * repository or any layer above it.
 */
class ImprovementLocalDataSource @Inject constructor() {

    fun getTips(): List<ImprovementTip> = TIPS

    private companion object {
        val TIPS = listOf(
            ImprovementTip(
                id = 1,
                title = "Start small",
                description = "Pick one tiny habit you can do in two minutes and do it today.",
            ),
            ImprovementTip(
                id = 2,
                title = "Reflect briefly",
                description = "Spend a minute noting one thing that went well and one to improve.",
            ),
            ImprovementTip(
                id = 3,
                title = "Move your body",
                description = "A short walk clears the mind and resets your focus.",
            ),
            ImprovementTip(
                id = 4,
                title = "Single-task",
                description = "Choose the most important thing and give it your full attention.",
            ),
            ImprovementTip(
                id = 5,
                title = "Rest on purpose",
                description = "Schedule a real break — recovery is part of improvement.",
            ),
        )
    }
}
