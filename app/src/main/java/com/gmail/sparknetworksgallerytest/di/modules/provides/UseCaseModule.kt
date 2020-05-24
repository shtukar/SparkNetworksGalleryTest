package com.gmail.sparknetworksgallerytest.di.modules.provides

import com.gmail.sparknetworksgallerytest.domain.repository.AuthRepository
import com.gmail.sparknetworksgallerytest.domain.repository.GalleryRepository
import com.gmail.sparknetworksgallerytest.domain.usecase.AuthUseCase
import com.gmail.sparknetworksgallerytest.domain.usecase.AuthUseCaseImpl
import com.gmail.sparknetworksgallerytest.domain.usecase.GalleryUseCase
import com.gmail.sparknetworksgallerytest.domain.usecase.GalleryUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun providesAuthUseCase(repository: AuthRepository): AuthUseCase = AuthUseCaseImpl(repository)

    @Provides
    fun providesGalleryUseCase(repository: GalleryRepository): GalleryUseCase = GalleryUseCaseImpl(repository)
}