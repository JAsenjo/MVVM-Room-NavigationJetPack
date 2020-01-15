package com.jasenjo.sdos.prueba.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jasenjo.sdos.databinding.FragmentEmployeeBinding
import com.jasenjo.sdos.prueba.viewmodel.MainViewModel
import com.jasenjo.sdos.prueba.widget.adapter.TaskAdapter
import kotlinx.android.synthetic.main.fragment_employee.*

class EmployeeFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentEmployeeBinding.inflate(inflater, container, false)
        viewModel =  ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.tasks.observe(this, Observer { tasks ->
            tasks_recyclerview.adapter = TaskAdapter(tasks)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_save_tasks.setOnClickListener{ saveTasks()}
        viewModel.getTaskForUserLogged()
    }

    private fun saveTasks() {
        viewModel.saveTasks()
    }

}