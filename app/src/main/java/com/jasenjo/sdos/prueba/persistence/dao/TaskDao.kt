package com.jasenjo.sdos.prueba.persistence.dao

import androidx.room.*
import com.jasenjo.sdos.prueba.global.ASIGNEE
import com.jasenjo.sdos.prueba.global.TABLE_NAME_IS_DONE
import com.jasenjo.sdos.prueba.global.TABLE_NAME_TASK
import com.jasenjo.sdos.prueba.persistence.entity.TaskEntity

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(taskEntity: TaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateAll(taskEntity: Array<TaskEntity>?)

    @Query("UPDATE $TABLE_NAME_TASK SET $TABLE_NAME_IS_DONE=:isDone WHERE ID=:taskId")
    fun updateTaskIsDone(isDone: Boolean, taskId: Long)

    @Delete
    fun delete(taskEntity: TaskEntity)

    @Query("SELECT * FROM $TABLE_NAME_TASK")
    fun getLastUpdateEntity(): TaskEntity

    @Query("SELECT * FROM $TABLE_NAME_TASK WHERE $ASIGNEE=:userId")
    fun getTasksForUser(userId: Long): Array<TaskEntity>

}