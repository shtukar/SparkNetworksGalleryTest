package com.gmail.sparknetworksgallerytest.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmail.sparknetworksgallerytest.domain.common.ResultState
import com.gmail.sparknetworksgallerytest.domain.usecase.AuthUseCase
import com.gmail.sparknetworksgallerytest.presentation.common.BaseViewModel
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authUseCase: AuthUseCase) : BaseViewModel() {

    private val signInStatus by lazy { MutableLiveData<ResultState<Boolean>>() }

    fun getSignInStatusStatus(): LiveData<ResultState<Boolean>> = signInStatus

    private val signUpStatus by lazy { MutableLiveData<ResultState<Boolean>>() }

    fun getSignUpStatusStatus(): LiveData<ResultState<Boolean>> = signUpStatus

    fun signIn(email: String, password: String) {
        authUseCase.signIn(email, password)
                .subscribe { result: ResultState<Boolean> ->
                    signInStatus.postValue(result)
                }
                .track()
    }

    fun signUp(email: String, password: String) {
        authUseCase.signUp(email, password)
                .subscribe { result: ResultState<Boolean> ->
                    signUpStatus.postValue(result)
                }
                .track()
    }
}