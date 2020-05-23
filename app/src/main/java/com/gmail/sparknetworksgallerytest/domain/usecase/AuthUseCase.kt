package com.gmail.sparknetworksgallerytest.domain.usecase

import com.gmail.sparknetworksgallerytest.domain.common.ResultState
import io.reactivex.Single

interface AuthUseCase {
    fun isCurrentUserExist(): Boolean

    fun signIn(email: String, password: String): Single<ResultState<Boolean>>

    fun signUp(email: String, password: String): Single<ResultState<Boolean>>
}
