package com.yourdailyimprovement.androidapp.domain

import com.yourdailyimprovement.androidapp.domain.model.ImprovementTip
import com.yourdailyimprovement.androidapp.domain.repository.ImprovementRepository
import com.yourdailyimprovement.androidapp.domain.usecase.GetDailyTipUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Demonstrates that the domain layer is testable in isolation: a fake repository
 * is injected into the real use case, no Android framework or Hilt required.
 */
class GetDailyTipUseCaseTest {

    private class FakeRepository(private val tip: ImprovementTip) : ImprovementRepository {
        override suspend fun getDailyTip(): ImprovementTip = tip
    }

    @Test
    fun `returns the tip provided by the repository`() = runTest {
        val expected = ImprovementTip(42, "Test title", "Test description")
        val useCase = GetDailyTipUseCase(FakeRepository(expected))

        val result = useCase()

        assertEquals(expected, result)
    }
}
