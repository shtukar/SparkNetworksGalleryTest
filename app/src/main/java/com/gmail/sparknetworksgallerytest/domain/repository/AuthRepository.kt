package com.gmail.sparknetworksgallerytest.domain.repository

import com.gmail.sparknetworksgallerytest.domain.common.ResultState
import io.reactivex.Single

interface AuthRepository {
    fun isCurrentUserExist(): Boolean

    fun signIn(email: String, password: String): Single<ResultState<Boolean>>

    fun signUp(email: String, password: String): Single<ResultState<Boolean>>

    fun logOut(): Single<ResultState<Boolean>>
}
