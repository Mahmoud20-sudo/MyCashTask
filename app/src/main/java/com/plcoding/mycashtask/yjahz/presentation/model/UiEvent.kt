package com.plcoding.mycashtask.yjahz.presentation.model

sealed class UiEvent {
    data class ShowSnackbar(val message: String): UiEvent()
    data object None: UiEvent()
    data object NavigateToNext: UiEvent()

}