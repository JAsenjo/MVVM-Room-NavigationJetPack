package com.jasenjo.sdos.prueba.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jasenjo.sdos.prueba.global.TABLE_NAME_LAST_UPDATE

@Entity(tableName = TABLE_NAME_LAST_UPDATE)
data class DateUpdateEntity(@ColumnInfo(name = "date_last_update") var lastUpdate: Long?) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0L
}