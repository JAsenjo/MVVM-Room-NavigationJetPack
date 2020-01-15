package com.jasenjo.sdos.prueba.persistence.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jasenjo.sdos.prueba.persistence.dao.DateUpdateDao
import com.jasenjo.sdos.prueba.persistence.dao.FruitDao
import com.jasenjo.sdos.prueba.persistence.dao.TaskDao
import com.jasenjo.sdos.prueba.persistence.dao.UserDao
import com.jasenjo.sdos.prueba.persistence.entity.DateUpdateEntity
import com.jasenjo.sdos.prueba.persistence.entity.FruitEntity
import com.jasenjo.sdos.prueba.persistence.entity.TaskEntity
import com.jasenjo.sdos.prueba.persistence.entity.UserEntity

@Database(entities = [UserEntity::class, FruitEntity::class, DateUpdateEntity::class, TaskEntity::class], version = 1)
abstract class SdosDatabase : RoomDatabase() {

    abstract fun fruitDao(): FruitDao
    abstract fun lastUpdateDao(): DateUpdateDao
    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao

    companion object {
        private val DATABASE_NAME = "sdos_database"
        @Volatile private var INSTANCE: SdosDatabase? = null

        fun getInstance(context: Context): SdosDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    SdosDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }

}