package com.jasenjo.sdos.prueba.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jasenjo.sdos.prueba.global.TABLE_NAME_USER

@Entity(tableName = TABLE_NAME_USER)
data class UserEntity(
    @PrimaryKey var id: Long,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "is_admin") val isAdmin: Boolean,
    @ColumnInfo(name = "hours_work") val hours: Int)