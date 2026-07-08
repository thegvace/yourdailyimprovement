package com.yourdailyimprovement.androidapp.domain.usecase

import com.yourdailyimprovement.androidapp.domain.model.ImprovementTip
import com.yourdailyimprovement.androidapp.domain.repository.ImprovementRepository
import javax.inject.Inject

/**
 * Returns today's improvement tip. A use case holds a single unit of business
 * logic and is the only thing a ViewModel talks to in the domain layer.
 */
class GetDailyTipUseCase @Inject constructor(
    private val repository: ImprovementRepository,
) {
    suspend operator fun invoke(): ImprovementTip = repository.getDailyTip()
}
