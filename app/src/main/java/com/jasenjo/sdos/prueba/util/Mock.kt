package com.jasenjo.sdos.prueba.util

import com.jasenjo.sdos.prueba.persistence.entity.UserEntity

fun getMockUsers(): MutableList<UserEntity> {
    val adminUser = UserEntity(1, "admin", "admin", true, 0)
    val jgarciaUser = UserEntity(2, "jgarcia", "1234", false, 0)
    val jfernandezUser = UserEntity(3, "jfernandez", "1234", false, 0)
    return mutableListOf(adminUser, jgarciaUser, jfernandezUser)
}