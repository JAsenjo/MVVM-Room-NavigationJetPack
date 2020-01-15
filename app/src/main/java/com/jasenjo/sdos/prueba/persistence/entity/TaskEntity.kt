package com.jasenjo.sdos.prueba.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jasenjo.sdos.prueba.global.TABLE_NAME_TASK

@Entity(tableName = TABLE_NAME_TASK)
data class TaskEntity(
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "duration") val hours: Int?,
    @ColumnInfo(name = "asignee") val asignee: Long?,
    @ColumnInfo(name = "is_done") var isDone: Boolean = false) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0L
}