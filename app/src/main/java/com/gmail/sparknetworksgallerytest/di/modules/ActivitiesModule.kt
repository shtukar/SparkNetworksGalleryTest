package com.gmail.sparknetworksgallerytest.di.modules

import com.gmail.sparknetworksgallerytest.presentation.ui.MainActivity
import com.gmail.sparknetworksgallerytest.presentation.ui.login.LoginActivity
import com.gmail.sparknetworksgallerytest.presentation.ui.splash.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun getSplashScreenActivity(): SplashScreenActivity

    @ContributesAndroidInjector
    abstract fun getLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun getMainActivity(): MainActivity
}
