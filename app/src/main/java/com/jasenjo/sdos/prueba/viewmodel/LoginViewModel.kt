package com.jasenjo.sdos.prueba.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jasenjo.sdos.prueba.global.KEY_IS_USER_LOGGED
import com.jasenjo.sdos.prueba.global.KEY_USER_LOGGED_ID
import com.jasenjo.sdos.prueba.persistence.database.SdosDatabase
import com.jasenjo.sdos.prueba.persistence.entity.UserEntity
import com.jasenjo.sdos.prueba.persistence.sharedpreference.SdosPreferenceManager
import com.jasenjo.sdos.prueba.viewmodel.state.LoginState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val LOG = LoggerFactory.getLogger(LoginViewModel::class.java)
    var username: ObservableField<String>? = ObservableField("")
    var password: ObservableField<String>? = ObservableField("")
    var viewModelState = MutableLiveData<LoginState>()
    val context = getApplication<Application>().applicationContext

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            val userLogged = SdosDatabase.getInstance(context)?.userDao()?.findUser(username?.get(), password?.get())
            withContext(Dispatchers.Main) {
                userLogged?.run {
                    onLoginSuccess(userLogged)
                }?: onLoginError()
            }
        }
    }

    private fun onLoginSuccess(userLogged: UserEntity) {
        SdosPreferenceManager(context).setBoolean(KEY_IS_USER_LOGGED, true)
        SdosPreferenceManager(context).setLong(KEY_USER_LOGGED_ID, userLogged.id)
        viewModelState.value = LoginState.ON_LOGIN_SUCCESS
    }

    private fun onLoginError() {
        viewModelState.value = LoginState.ON_LOGIN_ERROR
    }

}