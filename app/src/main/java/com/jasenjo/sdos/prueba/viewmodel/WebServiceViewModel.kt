package com.jasenjo.sdos.prueba.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jasenjo.sdos.prueba.persistence.database.SdosDatabase
import com.jasenjo.sdos.prueba.persistence.entity.FruitEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory

class WebServiceViewModel(application: Application) : AndroidViewModel(application) {

    private val LOG = LoggerFactory.getLogger(WebServiceViewModel::class.java)
    val context = getApplication<Application>().applicationContext
    var fruits = MutableLiveData<Array<FruitEntity>>()

    fun getFruits() {
        viewModelScope.launch(Dispatchers.IO) {
            fruits.postValue(SdosDatabase.getInstance(context)?.fruitDao()?.getFruits())
        }
    }

}