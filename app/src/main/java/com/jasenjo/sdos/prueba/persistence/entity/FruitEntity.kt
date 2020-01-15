package com.jasenjo.sdos.prueba.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jasenjo.sdos.prueba.global.TABLE_NAME_FRUIT

@Entity(tableName = TABLE_NAME_FRUIT)
data class FruitEntity(
    @ColumnInfo(name = "farmer_id") val farmerId: Int?,
    @ColumnInfo(name = "category") val category: String?,
    @ColumnInfo(name = "item") val item: String?,
    @ColumnInfo(name = "phone") val phone: String?
) {
    @PrimaryKey(autoGenerate = true) var id: Long? = null
}