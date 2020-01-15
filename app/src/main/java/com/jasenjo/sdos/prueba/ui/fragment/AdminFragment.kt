package com.jasenjo.sdos.prueba.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.jasenjo.sdos.R
import com.jasenjo.sdos.prueba.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_admin.*

class AdminFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = activity?.run { ViewModelProviders.of(this).get(MainViewModel::class.java) }?: throw Exception("Invalid Activity")
        configureAddTaskButton()
    }

    private fun configureAddTaskButton() {
        add_task_button.setOnClickListener {
            showCreateTaskDialog()
        }
    }

    private fun showCreateTaskDialog() {
        val dialog = activity?.let { Dialog(it, android.R.style.Theme_Light) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(R.layout.dialog_create_task)
        dialog?.findViewById<Button>(R.id.create_task_button)?.setOnClickListener {
            val description = dialog.findViewById<EditText>(R.id.task_description).text?: ""
            val durationTask = dialog.findViewById<EditText>(R.id.task_duration).text?: 0
            viewModel.saveCreatedTask(description.toString(), Integer.valueOf(durationTask.toString()))
            dialog.dismiss()
        }
        dialog?.findViewById<Button>(R.id.dismiss_create_task_button)?.setOnClickListener{ dialog.dismiss() }
        dialog?.show()
    }

}