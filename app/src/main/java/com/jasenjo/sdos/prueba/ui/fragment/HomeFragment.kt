package com.jasenjo.sdos.prueba.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.jasenjo.sdos.R
import com.jasenjo.sdos.prueba.persistence.sharedpreference.SdosPreferenceManager
import com.jasenjo.sdos.prueba.ui.activity.SplashActivity
import com.jasenjo.sdos.prueba.viewmodel.MainViewModel
import com.jasenjo.sdos.prueba.viewmodel.state.HomeState
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = activity?.run { ViewModelProviders.of(this).get(MainViewModel::class.java) }?: throw Exception("Invalid Activity")
        setButtonListeners()
        viewModel.homeState.observe(this, Observer { viewModelState ->
            when (viewModelState) {
                is HomeState.ON_ADMING_LOGGED -> onAdminLogged()
                is HomeState.ON_EMPLOYEE_LOGGED -> onEmployeeLogged()
            }
        })
    }

    private fun setButtonListeners() {
        button_admin_fragment.setOnClickListener { Navigation.findNavController(it).navigate(R.id.admin_fragment) }
        button_employee_fragment.setOnClickListener { Navigation.findNavController(it).navigate(R.id.employee_fragment) }
        button_webservice.setOnClickListener { Navigation.findNavController(it).navigate(R.id.webservice_fragment) }
        button_logout.setOnClickListener { onLogout() }
    }

    private fun onAdminLogged() {
        button_admin_fragment.visibility = View.VISIBLE
    }

    private fun onEmployeeLogged() {
        button_employee_fragment.visibility = View.VISIBLE
    }

    private fun onLogout() {
        context?.let { SdosPreferenceManager(it).clearSharedPreferences() }
        startActivity(Intent(activity, SplashActivity::class.java))
        activity?.finish()
    }

}