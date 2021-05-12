package com.example.androidinternshippart3.register

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.database.DataBase
import com.example.androidinternshippart3.databinding.FragmentRegisterBinding
import kotlinx.android.synthetic.main.activity_main.*

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


        return binding.root
    }

    private fun hideOtherFragments() {
        fragment.view?.setBackgroundColor(Color.WHITE);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideOtherFragments()
    }

}