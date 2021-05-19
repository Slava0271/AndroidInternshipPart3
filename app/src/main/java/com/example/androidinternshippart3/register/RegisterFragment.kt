package com.example.androidinternshippart3.register

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.database.DataBase
import com.example.androidinternshippart3.databinding.FragmentRegisterBinding
import com.example.androidinternshippart3.login.LoginFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRegisterBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_register, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSourceUsers = DataBase.getInstance(application).usersDao
        val dataSourceAccess = DataBase.getInstance(application).accessDao
        val dataSourceTests = DataBase.getInstance(application).testsDao
        val dataSourceQuestions = DataBase.getInstance(application).questionsDao
        val dataSourceAnswers = DataBase.getInstance(application).answersDao


        val viewModelFactory = RegisterFragmentFactory(
                dataSourceUsers,
                dataSourceAccess,
                dataSourceTests,
                dataSourceQuestions,
                dataSourceAnswers,
                application,
                this.requireActivity().supportFragmentManager,
                this.requireContext()
        )

        val registerViewModel =
                ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.registerModel = registerViewModel

        //registerViewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)



        return binding.root
    }

    private fun tabListener(){
        tabLayout1.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    1 -> findNavController().navigate(R.id.loginFragment)
                 //   0 -> changeFragment(fragmentRegister)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    private fun hideOtherFragments() {
        fragment.view?.setBackgroundColor(Color.WHITE);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideOtherFragments()
        tabListener()

    }

}