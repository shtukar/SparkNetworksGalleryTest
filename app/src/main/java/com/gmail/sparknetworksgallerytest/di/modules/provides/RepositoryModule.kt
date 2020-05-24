package com.gmail.sparknetworksgallerytest.di.modules.provides

import com.gmail.sparknetworksgallerytest.data.repository.AuthRepositoryImpl
import com.gmail.sparknetworksgallerytest.data.repository.GalleryRepositoryImpl
import com.gmail.sparknetworksgallerytest.domain.repository.AuthRepository
import com.gmail.sparknetworksgallerytest.domain.repository.GalleryRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    fun providesFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()

    @Provides
    fun providesAuthRepository(auth: FirebaseAuth): AuthRepository = AuthRepositoryImpl(auth)

    @Provides
    fun providesGalleryRepository(storage: FirebaseStorage, database: FirebaseDatabase,
                                  auth: FirebaseAuth): GalleryRepository =
            GalleryRepositoryImpl(storage, database, auth)
}