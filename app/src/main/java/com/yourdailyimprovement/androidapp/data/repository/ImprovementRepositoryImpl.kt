package com.yourdailyimprovement.androidapp.data.repository

import com.yourdailyimprovement.androidapp.data.local.ImprovementLocalDataSource
import com.yourdailyimprovement.androidapp.domain.model.ImprovementTip
import com.yourdailyimprovement.androidapp.domain.repository.ImprovementRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Implements the domain's [ImprovementRepository] by orchestrating data sources.
 * It decides *which* tip to surface; where the tips come from is the data
 * source's concern. Swapping [ImprovementLocalDataSource] for a remote/DB source
 * would not touch the domain or UI layers.
 */
class ImprovementRepositoryImpl @Inject constructor(
    private val localDataSource: ImprovementLocalDataSource,
) : ImprovementRepository {

    override suspend fun getDailyTip(): ImprovementTip {
        val tips = localDataSource.getTips()
        val dayOfEpoch = TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis())
        val index = (dayOfEpoch % tips.size).toInt()
        return tips[index]
    }
}
