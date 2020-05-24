package com.gmail.sparknetworksgallerytest.data.repository

import com.gmail.sparknetworksgallerytest.data.common.applyIoScheduler
import com.gmail.sparknetworksgallerytest.domain.common.ResultState
import com.gmail.sparknetworksgallerytest.domain.entity.ErrorState
import com.gmail.sparknetworksgallerytest.domain.repository.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Single

class AuthRepositoryImpl(private val auth: FirebaseAuth) : AuthRepository {

    override fun isCurrentUserExist(): Boolean {
        return auth.currentUser != null
    }

    override fun signIn(email: String, password: String): Single<ResultState<Boolean>> {
        return Single.create<ResultState<Boolean>> { subscriber ->
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task: Task<AuthResult?> ->
                        if (task.isSuccessful) {
                            subscriber.onSuccess(ResultState.Success(true))
                        }
                    }
                    .addOnFailureListener { e: Exception? ->
                        subscriber.onSuccess(ResultState.Error(
                                ErrorState(0, e?.localizedMessage.toString()), false))
                    }
        }.applyIoScheduler()
    }

    override fun signUp(email: String, password: String): Single<ResultState<Boolean>> {
        return Single.create<ResultState<Boolean>> { subscriber ->
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task: Task<AuthResult?> ->
                        if (task.isSuccessful) {
                            subscriber.onSuccess(ResultState.Success(true))
                        }
                    }
                    .addOnFailureListener { e: Exception? ->
                        subscriber.onSuccess(ResultState.Error(
                                ErrorState(0, e?.localizedMessage.toString()), false))
                    }
        }.applyIoScheduler()
    }

    override fun logOut(): Single<ResultState<Boolean>> {
        return Single.create<ResultState<Boolean>> { subscriber ->
            auth.signOut()
            subscriber.onSuccess(ResultState.Success(true))
        }
    }
}