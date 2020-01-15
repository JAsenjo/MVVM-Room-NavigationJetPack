package com.jasenjo.sdos.prueba.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.jasenjo.sdos.R
import com.jasenjo.sdos.prueba.global.KEY_IS_USER_LOGGED
import com.jasenjo.sdos.prueba.persistence.sharedpreference.SdosPreferenceManager
import com.jasenjo.sdos.prueba.services.intentservice.LoadDataIntentService
import org.slf4j.LoggerFactory

class SplashActivity : AppCompatActivity() {

    private val LOG = LoggerFactory.getLogger(SplashActivity::class.java)
    private val DURATION_SPLASH_SCREEN = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadData()
        fakeSplash()
    }

    private fun loadData() {
        val intent = Intent(this, LoadDataIntentService::class.java)
        LoadDataIntentService.enqueueWork(this, intent)
    }

    private fun fakeSplash() {
        Handler().postDelayed({
            isUserLogged()
        }, DURATION_SPLASH_SCREEN)
    }

    private fun isUserLogged() {
        if (SdosPreferenceManager(this).getBoolean(KEY_IS_USER_LOGGED)) {
            startActivity(Intent(this, MainActivity::class.java))
        } else startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}