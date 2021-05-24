package com.example.androidinternshippart3.user

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.room.Database
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.admin.tests.AdminTestsArgs
import com.example.androidinternshippart3.database.DataBase
import com.example.androidinternshippart3.databinding.FragmentUserBinding
import kotlinx.android.synthetic.main.activity_main.*


class UserFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentUserBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_user, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSourceAccess = DataBase.getInstance(application).accessDao
        val dataSourceUsers = DataBase.getInstance(application).usersDao
        val dataSourceResults = DataBase.getInstance(application).resultsDao

        val args = arguments
        val userId = UserFragmentArgs.fromBundle(args!!).userId

        Log.d("userId", userId.toString())


        val viewModelFactory = UserFragmentFactory(
                application,
                dataSourceAccess,
                userId,
                this.requireActivity().supportFragmentManager,
                dataSourceUsers,
                dataSourceResults
        )
        val userViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)



        binding.lifecycleOwner = viewLifecycleOwner
        binding.userViewModel = userViewModel



        userViewModel.navigationEventToFirstTest.observe(viewLifecycleOwner, ::navigate)
        userViewModel.navigationToLoginEvent.observe(viewLifecycleOwner, ::navigate)
        userViewModel.navigateToResults.observe(viewLifecycleOwner, ::navigate)
        userViewModel.navigateToSecondTest.observe(viewLifecycleOwner, ::navigate)
        userViewModel.navigateToThirdTest.observe(viewLifecycleOwner, ::navigate)

        return binding.root
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}