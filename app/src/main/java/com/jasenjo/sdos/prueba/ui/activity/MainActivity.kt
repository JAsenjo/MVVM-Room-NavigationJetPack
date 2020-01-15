package com.jasenjo.sdos.prueba.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.jasenjo.sdos.R
import com.jasenjo.sdos.prueba.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewModelProviders.of(this).get(MainViewModel::class.java)
        setContentView(R.layout.activity_main)
    }

}