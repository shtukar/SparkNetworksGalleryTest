package com.gmail.sparknetworksgallerytest.di.modules

import com.gmail.sparknetworksgallerytest.presentation.common.LoadingFragment
import com.gmail.sparknetworksgallerytest.presentation.ui.gallery.GalleryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun getLoadingFragment(): LoadingFragment

    @ContributesAndroidInjector
    abstract fun getGalleryFragment(): GalleryFragment
}
