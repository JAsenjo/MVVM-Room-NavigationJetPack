package com.jasenjo.sdos.prueba.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jasenjo.sdos.prueba.global.*
import com.jasenjo.sdos.prueba.persistence.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    fun insert(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAll(users: List<UserEntity>)

    @Update
    fun update(userEntity: UserEntity)

    @Query("UPDATE $TABLE_NAME_USER SET $TABLE_HOURS_WORK=:hours WHERE ID=:userId")
    fun updateHourForUser(hours: Int, userId: Long)

    @Delete
    fun delete(user: UserEntity)

    @Query("SELECT * FROM $TABLE_NAME_USER")
    fun getUsers(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM $TABLE_NAME_USER WHERE $ID=:id")
    fun getUserById(id: Long): UserEntity

    @Query("SELECT * FROM $TABLE_NAME_USER WHERE $USERNAME=:username AND $PASSWORD=:password")
    fun findUser(username: String?, password: String?): UserEntity

    @Query("SELECT * FROM $TABLE_NAME_USER WHERE $IS_ADMIN=0 ORDER BY $TABLE_HOURS_WORK ASC LIMIT 1")
    fun getUserWithLessWork(): UserEntity

}