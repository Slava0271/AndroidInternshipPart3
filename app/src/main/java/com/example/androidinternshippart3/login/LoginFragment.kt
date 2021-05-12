package com.example.androidinternshippart3.login

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.admin.AdminFragment
import com.example.androidinternshippart3.database.DataBase
import com.example.androidinternshippart3.databinding.FragmentLoginBinding
import com.example.androidinternshippart3.user.UserFragment
import kotlinx.android.synthetic.main.activity_main.*


class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )
        val application = requireNotNull(this.activity).application

        val dataSourceUsers = DataBase.getInstance(application).usersDao
        val dataSourceAccess = DataBase.getInstance(application).accessDao

        val viewModelFactory = LoginFragmentFactory(
            dataSourceUsers,
            dataSourceAccess,
            application,
            this.requireActivity().supportFragmentManager
        )

        val loginViewModel =
            ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.loginViewModel = loginViewModel

        loginViewModel.navigateEventToAdmin.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.adminFragment, null)
        }
        loginViewModel.navigateEventToUser.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.userFragment, sendData(loginViewModel.userId))
            //val fragment = UserFragment()
            //changeFragment(fragment)
        }
        loginViewModel.navigateEventManager.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.managerFragment)
        }

        return binding.root

    }

    private fun sendData(int: Int): Bundle {
        val bundle = Bundle()
        bundle.putString("number", int.toString())
        return bundle
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideOtherFragments()
    }

    private fun hideOtherFragments() {
        fragment.view?.setBackgroundColor(Color.WHITE);
    }

}