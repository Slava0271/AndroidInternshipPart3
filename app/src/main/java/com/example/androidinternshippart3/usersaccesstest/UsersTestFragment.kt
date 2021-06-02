package com.example.androidinternshippart3.usersaccesstest

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.database.DataBase
import com.example.androidinternshippart3.databinding.FragmentUsersTestBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_users_test.*


class UsersTestFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentUsersTestBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_users_test, container, false
        )
        val position = arguments?.getString("position")!!.toInt()
        val application = requireNotNull(this.activity).application

        val dataSourceUsers = DataBase.getInstance(application).usersDao
        val dataSourceAccess = DataBase.getInstance(application).accessDao
        val dataSourceTests = DataBase.getInstance(application).testsDao

        val viewModelFactory = UsersTestFragmentFactory(
                dataSourceUsers,
                dataSourceAccess,
                dataSourceTests,
                application,
                this.requireActivity().supportFragmentManager,
                position
        )

        val userTestViewModel =
                ViewModelProvider(this, viewModelFactory).get(UsersTestViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.userTestViewModel = userTestViewModel

        userTestViewModel.firstCheckBoxEvent.observe(viewLifecycleOwner) {
            userTestViewModel.isClickedOnStart(3, checkBoxThirdTest)
            checkBoxFirstTest.setOnCheckedChangeListener { _, isChecked ->
                userTestViewModel.onCheckBoxesClicked(isChecked, 1)
            }
            // do something with value
        }

        userTestViewModel.secondCheckBoxEvent.observe(viewLifecycleOwner) { value ->
            userTestViewModel.isClickedOnStart(1, checkBoxFirstTest)
            checkBoxSecondTest.setOnCheckedChangeListener { _, isChecked ->
                userTestViewModel.onCheckBoxesClicked(isChecked, 2)
            }
        }

        userTestViewModel.thirdCheckBoxEvent.observe(viewLifecycleOwner) { value ->
            userTestViewModel.isClickedOnStart(2, checkBoxSecondTest)
            checkBoxThirdTest.setOnCheckedChangeListener { _, isChecked ->
                userTestViewModel.onCheckBoxesClicked(isChecked, 3)
            }
        }

        userTestViewModel.radioGroupEvent.observe(viewLifecycleOwner) { value ->
            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                userTestViewModel.listenRadioGroup(checkedId)
            }
        }
        userTestViewModel.managerEvent.observe(viewLifecycleOwner) {
            userTestViewModel.getRadioOnStart(radioButton2, radioButton)
        }
        userTestViewModel.userEvent.observe(viewLifecycleOwner) {
            userTestViewModel.getRadioOnStart(radioButton2, radioButton)

        }

        userTestViewModel.navigateToUsersEvent.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.adminsUsers, null)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideOtherFragments()
    }

    private fun hideOtherFragments() {
        fragment.view?.setBackgroundColor(Color.WHITE);
    }

}