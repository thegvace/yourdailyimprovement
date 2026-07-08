package com.yourdailyimprovement.androidapp.domain.repository

import com.yourdailyimprovement.androidapp.domain.model.ImprovementTip

/**
 * Abstraction over the source of improvement tips. Defined in the domain layer
 * so the domain and UI depend only on this interface, never on a concrete
 * data-layer implementation.
 */
interface ImprovementRepository {
    /** Returns the tip to surface for the current day. */
    suspend fun getDailyTip(): ImprovementTip
}
