package com.yourdailyimprovement.androidapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourdailyimprovement.androidapp.domain.usecase.GetDailyTipUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Holds the home screen's UI state and exposes it as a [StateFlow]. Depends only
 * on the domain layer (the use case) — it knows nothing about where the data
 * comes from.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDailyTip: GetDailyTipUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadTip()
    }

    fun loadTip() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            _uiState.value = try {
                HomeUiState.Success(getDailyTip())
            } catch (e: Exception) {
                HomeUiState.Error(e.message ?: "Something went wrong")
            }
        }
    }
}
