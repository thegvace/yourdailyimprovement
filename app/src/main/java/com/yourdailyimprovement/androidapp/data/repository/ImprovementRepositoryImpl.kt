package com.yourdailyimprovement.androidapp.data.repository

import com.yourdailyimprovement.androidapp.domain.model.ImprovementTip
import com.yourdailyimprovement.androidapp.domain.repository.ImprovementRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * In-memory implementation of [ImprovementRepository]. This is a placeholder
 * data source for Milestone 1 — a later milestone can swap it for a local
 * database or a remote API without touching the domain or UI layers.
 */
class ImprovementRepositoryImpl @Inject constructor() : ImprovementRepository {

    private val tips = listOf(
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

    override suspend fun getDailyTip(): ImprovementTip {
        val dayOfEpoch = TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis())
        val index = (dayOfEpoch % tips.size).toInt()
        return tips[index]
    }
}
