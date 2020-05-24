package com.gmail.sparknetworksgallerytest.auth

import com.gmail.sparknetworksgallerytest.*
import com.gmail.sparknetworksgallerytest.domain.common.ResultState
import com.gmail.sparknetworksgallerytest.domain.entity.ErrorState
import com.gmail.sparknetworksgallerytest.domain.usecase.AuthUseCase
import com.gmail.sparknetworksgallerytest.presentation.ui.login.AuthViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito

class AuthViewModelTest : BaseTest() {

    @Mock
    lateinit var authUseCase: AuthUseCase

    @InjectMocks
    lateinit var viewModel: AuthViewModel

    @Test
    fun `sign in`() {
        val resultSuccess: ResultState<Boolean> = ResultState.Success(true)

        Mockito.`when`(authUseCase.signIn(TEST_EMAIL, TEST_PASSWORD))
                .thenReturn(Single.just(resultSuccess))
        viewModel.signIn(TEST_EMAIL, TEST_PASSWORD)
        Assert.assertEquals(resultSuccess, viewModel.getSignInStatus().value)
    }

    @Test
    fun `sign in error`() {
        val resultError: ResultState<Boolean> =
                ResultState.Error(ErrorState(TEST_CODE_ERROR, TEST_MESSAGE_ERROR), null)

        Mockito.`when`(authUseCase.signIn(TEST_EMAIL, TEST_PASSWORD))
                .thenReturn(Single.just(resultError))
        viewModel.signIn(TEST_EMAIL, TEST_PASSWORD)
        Assert.assertEquals(resultError, viewModel.getSignInStatus().value)
    }

    @Test
    fun `sign up`() {
        val resultSuccess: ResultState<Boolean> = ResultState.Success(true)

        Mockito.`when`(authUseCase.signUp(TEST_EMAIL, TEST_PASSWORD))
                .thenReturn(Single.just(resultSuccess))
        viewModel.signUp(TEST_EMAIL, TEST_PASSWORD)
        Assert.assertEquals(resultSuccess, viewModel.getSignUpStatus().value)
    }

    @Test
    fun `sign up error`() {
        val resultError: ResultState<Boolean> =
                ResultState.Error(ErrorState(TEST_CODE_ERROR, TEST_MESSAGE_ERROR), null)

        Mockito.`when`(authUseCase.signUp(TEST_EMAIL, TEST_PASSWORD))
                .thenReturn(Single.just(resultError))
        viewModel.signUp(TEST_EMAIL, TEST_PASSWORD)
        Assert.assertEquals(resultError, viewModel.getSignUpStatus().value)
    }

}