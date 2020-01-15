package com.jasenjo.sdos.prueba.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jasenjo.sdos.R
import com.jasenjo.sdos.databinding.ActivityLoginBinding
import com.jasenjo.sdos.prueba.viewmodel.LoginViewModel
import com.jasenjo.sdos.prueba.viewmodel.state.LoginState
import org.slf4j.LoggerFactory

class LoginActivity : AppCompatActivity() {

    private val LOG = LoggerFactory.getLogger(LoginActivity::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView(this, R.layout.activity_login) as ActivityLoginBinding
        binding.viewmodel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.viewmodel?.viewModelState?.observe(this, Observer { viewModelState ->
            when (viewModelState) {
                is LoginState.ON_LOGIN_SUCCESS -> navigateToHome()
                is LoginState.ON_LOGIN_ERROR -> showLoginError()
            }
        })
    }

    private fun showLoginError() {
        Toast.makeText(this, getString(R.string.activity_login_error_message), Toast.LENGTH_SHORT).show()
    }

    private fun navigateToHome() {
        Toast.makeText(this, getString(R.string.activity_login_success_message), Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}