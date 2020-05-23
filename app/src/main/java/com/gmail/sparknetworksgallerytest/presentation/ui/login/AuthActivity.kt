package com.gmail.sparknetworksgallerytest.presentation.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gmail.sparknetworksgallerytest.R
import com.gmail.sparknetworksgallerytest.domain.common.ResultState
import com.gmail.sparknetworksgallerytest.presentation.common.BaseActivity
import com.gmail.sparknetworksgallerytest.presentation.extentions.observe
import com.gmail.sparknetworksgallerytest.presentation.ui.getMainActivityLaunchIntent
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

fun Context.getLoginActivityLaunchIntent(): Intent {
    val intent = Intent(this, LoginActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    return intent
}

class LoginActivity : BaseActivity() {

    companion object {
        const val TAG = "LoginActivity"
    }

    override val layoutId: Int = R.layout.activity_login

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: AuthViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(AuthViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        observe(viewModel.getSignInStatusStatus(), ::onAuth)
        observe(viewModel.getSignUpStatusStatus(), ::onAuth)

        btnSignIn.setOnClickListener {
            if (validateFields()) {
                showLoading()
                viewModel.signIn(etEmail.text.toString(), etPassword.text.toString())
            }
        }
        btnSignUp.setOnClickListener {
            if (validateFields()) {
                showLoading()
                viewModel.signUp(etEmail.text.toString(), etPassword.text.toString())
            }
        }

    }

    private fun validateFields(): Boolean {
        val emailValid = if (etEmail.text?.isNotEmpty() == true) {
            if (isEmailValid(etEmail.text.toString())) {
                true
            } else {
                emailWrapper.error = getString(R.string.text_error_email_is_invalid)
                false
            }
        } else {
            emailWrapper.error = getString(R.string.text_required_field)
            false
        }

        val passwordValid = if (etPassword.text?.isNotEmpty() == true) {
            true
        } else {
            passwordWrapper.error = getString(R.string.text_required_field)
            false
        }

        return emailValid && passwordValid
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun onAuth(result: ResultState<Boolean>) {
        hideLoading()
        if (result is ResultState.Success) {
            startActivity(getMainActivityLaunchIntent())
            finishAffinity()
        } else if (result is ResultState.Error) {
            Log.e(TAG, result.error.message)
            Toast.makeText(this, getString(R.string.default_error), Toast.LENGTH_SHORT).show()
        }
    }
}
