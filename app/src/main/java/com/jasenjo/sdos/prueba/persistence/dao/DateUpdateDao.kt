package com.jasenjo.sdos.prueba.persistence.dao

import androidx.room.*
import com.jasenjo.sdos.prueba.global.TABLE_NAME_LAST_UPDATE
import com.jasenjo.sdos.prueba.persistence.entity.DateUpdateEntity

@Dao
interface DateUpdateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(date: DateUpdateEntity)

    @Update
    fun update(vararg date: DateUpdateEntity)

    @Delete
    fun delete(vararg date: DateUpdateEntity)

    @Query("SELECT * FROM $TABLE_NAME_LAST_UPDATE")
    fun getLastUpdateEntity(): DateUpdateEntity

}