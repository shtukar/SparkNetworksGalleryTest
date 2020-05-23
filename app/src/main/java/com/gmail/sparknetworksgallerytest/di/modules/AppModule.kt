package com.gmail.sparknetworksgallerytest.di.modules

import android.content.Context
import com.gmail.sparknetworksgallerytest.App
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideApplicationContext(app: App): Context
}
