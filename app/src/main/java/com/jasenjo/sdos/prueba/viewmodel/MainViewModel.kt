package com.jasenjo.sdos.prueba.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jasenjo.sdos.prueba.global.KEY_USER_LOGGED_ID
import com.jasenjo.sdos.prueba.persistence.database.SdosDatabase
import com.jasenjo.sdos.prueba.persistence.entity.TaskEntity
import com.jasenjo.sdos.prueba.persistence.entity.UserEntity
import com.jasenjo.sdos.prueba.persistence.sharedpreference.SdosPreferenceManager
import com.jasenjo.sdos.prueba.viewmodel.state.HomeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val LOG = LoggerFactory.getLogger(MainViewModel::class.java)
    private val ADMIN_ID = 1L
    private var userLogged: UserEntity? = null
    private val context = application.applicationContext
    private val userLoggedId = SdosPreferenceManager(context).getLong(KEY_USER_LOGGED_ID)
    var tasks = MutableLiveData<Array<TaskEntity>>()
    var homeState = MutableLiveData<HomeState>()

    init {
        isAdminOrEmployeeLogged()
        getUserLogged()
    }

    private fun isAdminOrEmployeeLogged() {
        val loggedState = if (userLoggedId == ADMIN_ID) HomeState.ON_ADMING_LOGGED else HomeState.ON_EMPLOYEE_LOGGED
        homeState.value = loggedState
    }

    private fun getUserLogged() {
        viewModelScope.launch(Dispatchers.IO) {
            userLogged = SdosDatabase.getInstance(context)?.userDao()?.getUserById(userLoggedId)
        }
    }

    private fun updateHoursOfAssignedWorker(userWithLessWork: UserEntity, taskDuration: Int) {
        val hoursOfWork = userWithLessWork.hours.plus(taskDuration)
        SdosDatabase.getInstance(context)?.userDao()?.updateHourForUser(hoursOfWork, userWithLessWork.id)
    }

    fun getTaskForUserLogged() {
        viewModelScope.launch(Dispatchers.IO) {
            tasks.postValue(SdosDatabase.getInstance(context)?.taskDao()?.getTasksForUser(userLoggedId))
        }
    }

    fun saveCreatedTask(taskDescription: String, taskDuration: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val userWithLessWork = SdosDatabase.getInstance(context)?.userDao()?.getUserWithLessWork()
            userWithLessWork?.run {
                val taskCreated = TaskEntity(taskDescription, taskDuration, userWithLessWork.id)
                SdosDatabase.getInstance(context)?.taskDao()?.insert(taskCreated)
                updateHoursOfAssignedWorker(userWithLessWork, taskDuration)
            }
        }
    }

    fun saveTasks() {
        viewModelScope.launch(Dispatchers.IO) {
           SdosDatabase.getInstance(context)?.taskDao()?.updateAll(tasks.value)
        }
    }

}