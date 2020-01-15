package com.jasenjo.sdos.prueba.widget.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jasenjo.sdos.R
import com.jasenjo.sdos.databinding.ItemTaskBinding
import com.jasenjo.sdos.prueba.persistence.entity.TaskEntity
import com.jasenjo.sdos.prueba.viewmodel.MainViewModel
import org.slf4j.LoggerFactory

class TaskAdapter(private val tasks: Array<TaskEntity>?): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val LOG = LoggerFactory.getLogger(MainViewModel::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_task, parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.taskBinding.task = tasks?.get(position)
        holder.checkBox.setOnCheckedChangeListener { _, isChecked -> tasks?.get(position)?.run { isDone = isChecked } }
    }

    override fun getItemCount(): Int {
        return tasks?.size?: 0
    }

    inner class TaskViewHolder(val taskBinding: ItemTaskBinding) : RecyclerView.ViewHolder(taskBinding.root) {
        val checkBox: CheckBox = taskBinding.root.findViewById(R.id.checkbox_task_is_done)
    }

}