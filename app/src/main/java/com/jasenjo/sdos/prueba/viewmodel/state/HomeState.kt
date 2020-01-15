package com.jasenjo.sdos.prueba.viewmodel.state

sealed class HomeState {

    object ON_ADMING_LOGGED : HomeState()
    object ON_EMPLOYEE_LOGGED : HomeState()

}