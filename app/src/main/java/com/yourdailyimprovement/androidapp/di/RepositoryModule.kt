package com.yourdailyimprovement.androidapp.di

import com.yourdailyimprovement.androidapp.data.repository.ImprovementRepositoryImpl
import com.yourdailyimprovement.androidapp.domain.repository.ImprovementRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Binds domain-layer interfaces to their data-layer implementations. This is the
 * single place that knows which concrete repository the app uses, so swapping an
 * implementation only touches this module.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindImprovementRepository(
        impl: ImprovementRepositoryImpl,
    ): ImprovementRepository
}
