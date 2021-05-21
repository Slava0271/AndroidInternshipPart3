package com.example.androidinternshippart3.login

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.database.DataBase
import com.example.androidinternshippart3.databinding.FragmentLoginBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*


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

        loginViewModel.navigationEventToAdmin.observe(viewLifecycleOwner, ::navigate)
        loginViewModel.navigateEventToUser.observe(viewLifecycleOwner, ::navigate)
        loginViewModel.navigateEventManager.observe(viewLifecycleOwner, ::navigate)


//        loginViewModel.navigateEventToUser.observe(viewLifecycleOwner) {
//            findNavController().navigate(R.id.userFragment, sendData(loginViewModel.userId))
//            //val fragment = UserFragment()
//            //changeFragment(fragment)
//        }


        return binding.root

    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    private fun tabListener() {
        val tab: TabLayout.Tab? = tabLayout2.getTabAt(1)
        tab!!.select()
        tabLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> findNavController().navigate(R.id.registerFragment)
                    //   0 -> changeFragment(fragmentRegister)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun sendData(int: Int): Bundle {
        val bundle = Bundle()
        bundle.putString("number", int.toString())
        return bundle
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideOtherFragments()
        tabListener()
    }

    private fun hideOtherFragments() {
        fragment.view?.setBackgroundColor(Color.WHITE);
    }

}