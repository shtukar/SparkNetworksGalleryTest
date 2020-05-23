package com.gmail.sparknetworksgallerytest.di.modules

import com.gmail.sparknetworksgallerytest.presentation.common.LoadingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun getLoadingFragment(): LoadingFragment
}
