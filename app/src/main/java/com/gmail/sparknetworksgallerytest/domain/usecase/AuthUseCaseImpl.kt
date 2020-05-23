package com.gmail.sparknetworksgallerytest.domain.usecase

import com.gmail.sparknetworksgallerytest.domain.common.ResultState
import com.gmail.sparknetworksgallerytest.domain.repository.AuthRepository
import io.reactivex.Single

class AuthUseCaseImpl(private val repository: AuthRepository) : AuthUseCase {

    override fun isCurrentUserExist(): Boolean {
        return repository.isCurrentUserExist()
    }

    override fun signIn(email: String, password: String): Single<ResultState<Boolean>> {
        return repository.signIn(email, password)
    }

    override fun signUp(email: String, password: String): Single<ResultState<Boolean>> {
        return repository.signUp(email, password)
    }
}
