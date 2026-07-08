package com.yourdailyimprovement.androidapp.ui.home

import com.yourdailyimprovement.androidapp.domain.model.ImprovementTip

/**
 * Immutable representation of everything the home screen needs to render.
 * Modelled as a sealed interface so the UI handles each state exhaustively.
 */
sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val tip: ImprovementTip) : HomeUiState
    data class Error(val message: String) : HomeUiState
}
