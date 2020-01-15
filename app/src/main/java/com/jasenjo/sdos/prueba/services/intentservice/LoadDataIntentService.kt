package com.jasenjo.sdos.prueba.services.intentservice

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import com.jasenjo.sdos.prueba.data.model.FruitModel
import com.jasenjo.sdos.prueba.global.LOAD_DATA_JOB_ID
import com.jasenjo.sdos.prueba.persistence.database.SdosDatabase
import com.jasenjo.sdos.prueba.persistence.entity.DateUpdateEntity
import com.jasenjo.sdos.prueba.services.RetrofitFactory
import com.jasenjo.sdos.prueba.services.interfaces.FruitService
import com.jasenjo.sdos.prueba.util.fromFruitModelToFruitEntity
import com.jasenjo.sdos.prueba.util.getMockUsers
import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.TimeUnit

class LoadDataIntentService : JobIntentService() {

    private val HOURS_TO_UPDATE = 24
    private val ITEMS_TO_SAVE_IN_DB = 100

    companion object {
        private val LOG = LoggerFactory.getLogger(LoadDataIntentService::class.java)

        fun enqueueWork(context: Context, intent: Intent) {
            LOG.debug("enqueueWork")
            enqueueWork(context, LoadDataIntentService::class.java, LOAD_DATA_JOB_ID, intent)
        }
    }

    override fun onHandleWork(intent: Intent) {
        LOG.debug("onHandleWork")
        if (updateDataIfNeeded()) {
            addFakeUsers()
            updateData()
        }
    }

    private fun addFakeUsers() {
        SdosDatabase.getInstance(applicationContext)?.userDao()?.insertAll(getMockUsers())
    }

    private fun updateDataIfNeeded(): Boolean {
        var needUpdate = true
        val lastUpdateEntity = SdosDatabase.getInstance(applicationContext)?.lastUpdateDao()?.getLastUpdateEntity()
        lastUpdateEntity?.lastUpdate?.let { it ->
            val currentDate = Date(System.currentTimeMillis())
            val diffInMilliseconds =  Date(it).time.minus(currentDate.time)
            diffInMilliseconds.let {
                if (TimeUnit.MILLISECONDS.toHours(it) <= HOURS_TO_UPDATE) {
                    needUpdate = false
                }
            }
        }
        return needUpdate
    }

    private fun updateData() {
        LOG.debug("updateData")
        val service = RetrofitFactory.getRetrofitService().create(FruitService::class.java)
        val call = service.getFruits()
        call.subscribe(
            { fruits -> run {
                    LOG.debug("onSuccess")
                    saveDataOnBD(fruits) }
            }, { error -> LOG.debug(error.printStackTrace().toString()) })
    }

    private fun saveDataOnBD(fruits: List<FruitModel>) {
        val fruitEntities = fromFruitModelToFruitEntity(fruits.take(ITEMS_TO_SAVE_IN_DB))
        SdosDatabase.getInstance(applicationContext)?.fruitDao()?.insertAll(fruitEntities)
        saveLastUpdateInBD()
    }

    private fun saveLastUpdateInBD() {
        SdosDatabase.getInstance(applicationContext)?.lastUpdateDao()?.insert(DateUpdateEntity(System.currentTimeMillis()))
    }

}