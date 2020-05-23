package com.gmail.sparknetworksgallerytest.presentation.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gmail.sparknetworksgallerytest.R
import com.gmail.sparknetworksgallerytest.presentation.ui.getMainActivityLaunchIntent
import com.gmail.sparknetworksgallerytest.presentation.ui.login.getLoginActivityLaunchIntent
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashScreenActivity : DaggerAppCompatActivity() {

    companion object {
        const val DELAY_2000 = 2000L
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SplashScreenViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SplashScreenViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            if (viewModel.isCurrentUserExist()) {
                startActivity(getMainActivityLaunchIntent())
            } else {
                startActivity(getLoginActivityLaunchIntent())
            }
            finishAffinity()
        }, DELAY_2000)
    }
}
