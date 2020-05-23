package com.gmail.sparknetworksgallerytest.presentation.ui.splash

import com.gmail.sparknetworksgallerytest.domain.usecase.AuthUseCase
import com.gmail.sparknetworksgallerytest.presentation.common.BaseViewModel
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(private val authUseCase: AuthUseCase) : BaseViewModel() {

    fun isCurrentUserExist(): Boolean {
        return authUseCase.isCurrentUserExist()
    }
}