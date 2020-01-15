package com.jasenjo.sdos.prueba.viewmodel.state

sealed class LoginState {

    object ON_LOGIN_SUCCESS : LoginState()
    object ON_LOGIN_ERROR : LoginState()

}