package com.gmail.sparknetworksgallerytest.di

import com.gmail.sparknetworksgallerytest.App
import com.gmail.sparknetworksgallerytest.di.modules.ActivitiesModule
import com.gmail.sparknetworksgallerytest.di.modules.AppModule
import com.gmail.sparknetworksgallerytest.di.modules.FragmentsModule
import com.gmail.sparknetworksgallerytest.di.modules.ViewModelModule
import com.gmail.sparknetworksgallerytest.di.modules.provides.RepositoryModule
import com.gmail.sparknetworksgallerytest.di.modules.provides.UseCaseModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ViewModelModule::class,
    FragmentsModule::class,
    ActivitiesModule::class,
    AppModule::class,
    RepositoryModule::class,
    UseCaseModule::class
])

interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<App>
}