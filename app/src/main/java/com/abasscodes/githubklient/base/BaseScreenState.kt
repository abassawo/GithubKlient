package com.abasscodes.githubklient.base

interface ScreenState

sealed class BaseScreenState : ScreenState {
    object Loading : BaseScreenState()
    data class Error(val errorMsg: Throwable) : BaseScreenState()
}