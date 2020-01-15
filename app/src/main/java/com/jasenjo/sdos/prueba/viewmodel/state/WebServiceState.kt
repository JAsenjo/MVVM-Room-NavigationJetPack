package com.jasenjo.sdos.prueba.viewmodel.state

import com.jasenjo.sdos.prueba.data.model.FruitModel

sealed class WebServiceState {

    class ON_LOADED_DATA(val fruits: List<FruitModel>): WebServiceState()

}