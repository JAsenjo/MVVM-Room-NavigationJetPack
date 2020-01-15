package com.jasenjo.sdos.prueba.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jasenjo.sdos.databinding.FragmentWebserviceBinding
import com.jasenjo.sdos.prueba.viewmodel.WebServiceViewModel
import com.jasenjo.sdos.prueba.widget.adapter.FruitAdapter
import kotlinx.android.synthetic.main.fragment_webservice.*

class WebServiceFragment : Fragment() {

    private lateinit var viewModel: WebServiceViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentWebserviceBinding.inflate(inflater, container, false)
        viewModel =  ViewModelProviders.of(requireActivity()).get(WebServiceViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.fruits.observe(this, Observer { fruits ->
            fruits_recyclerview.adapter = FruitAdapter(fruits)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFruits()
    }

}