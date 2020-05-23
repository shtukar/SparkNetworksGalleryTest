package com.gmail.sparknetworksgallerytest.di.modules.provides

import com.gmail.sparknetworksgallerytest.data.repository.AuthRepositoryImpl
import com.gmail.sparknetworksgallerytest.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesAuthRepository(auth: FirebaseAuth): AuthRepository = AuthRepositoryImpl(auth)
}