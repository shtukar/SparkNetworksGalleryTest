package com.gmail.sparknetworksgallerytest.splash_screen

import com.gmail.sparknetworksgallerytest.BaseTest
import com.gmail.sparknetworksgallerytest.domain.usecase.AuthUseCase
import com.gmail.sparknetworksgallerytest.presentation.ui.splash.SplashScreenViewModel
import org.junit.Assert
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito

class SplashScreenViewModelTest : BaseTest() {

    @Mock
    lateinit var authUseCase: AuthUseCase

    @InjectMocks
    lateinit var viewModel: SplashScreenViewModel

    @Test
    fun `is current user exist`() {
        Mockito.`when`(authUseCase.isCurrentUserExist()).thenReturn(true)
        Assert.assertTrue(viewModel.isCurrentUserExist())
    }

    @Test
    fun `is current user not exist`() {
        Mockito.`when`(authUseCase.isCurrentUserExist()).thenReturn(false)
        Assert.assertFalse(viewModel.isCurrentUserExist())
    }
}