package com.jasenjo.sdos.prueba.persistence.dao

import androidx.room.*
import com.jasenjo.sdos.prueba.global.TABLE_NAME_FRUIT
import com.jasenjo.sdos.prueba.persistence.entity.FruitEntity

@Dao
interface FruitDao {

    @Insert
    fun insert(fruit: FruitEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(fruits: List<FruitEntity>)

    @Update
    fun update(vararg fruit: FruitEntity)

    @Delete
    fun delete(vararg fruit: FruitEntity)

    @Query("SELECT * FROM $TABLE_NAME_FRUIT")
    fun getFruits(): Array<FruitEntity>?

}