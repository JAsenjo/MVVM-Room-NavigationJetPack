package com.jasenjo.sdos.prueba.app

import android.app.Application
import android.os.Build
import android.os.Environment
import com.facebook.stetho.Stetho
import com.jasenjo.sdos.R
import org.slf4j.LoggerFactory

class SdosApplication: Application() {

    private var logPath = ""

    override fun onCreate() {
        super.onCreate()
        initStetho()
        initLog()
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun initLog() {
        val LOGBACK_LOG_DIR = "LOGBACK_LOG_DIR"
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            val file = applicationContext.getExternalFilesDir(null)
            if (file != null) {
                logPath = file.absolutePath
            }
            System.setProperty(LOGBACK_LOG_DIR, logPath)
        }
        logAppAndDeviceInfo()
    }

    private fun logAppAndDeviceInfo() {
        val logger = LoggerFactory.getLogger(SdosApplication::class.java)
        logger.info("INIT APP")
        logger.info("packageInfo: {}", packageName + "-" + getString(R.string.app_name))
        logger.info("appVersionInfo: {}", packageManager.getPackageInfo(packageName, 0).versionName)
        logger.info("deviceModelInfo: {}", Build.MANUFACTURER + "-" + Build.MODEL)
        logger.info("androidVersionInfo: {}", Build.VERSION.RELEASE + "-" + Build.VERSION.SDK_INT)
    }

}