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
import androidx.navigation.fragment.findNavController
import androidx.room.Database
import com.example.androidinternshippart3.R
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
        val userId = arguments?.getString("number")!!.toInt()


        val application = requireNotNull(this.activity).application

        val dataSourceAccess = DataBase.getInstance(application).accessDao
        val dataSourceUsers = DataBase.getInstance(application).usersDao
        val dataSourceResults = DataBase.getInstance(application).resultsDao

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

        userViewModel.navigateToLoginEvent.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.loginFragment)
        }

        userViewModel.navigateToFirstTest.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.firstQuestion, sendData(userId))
        }
        userViewModel.navigateToResults.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.scoreFragment, sendData(userId))
        }
        userViewModel.navigateToSecondTest.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.fourhQuestionFragment, sendData(userId))
        }
        userViewModel.navigateToThirdTest.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.sevenQuestionFragment, sendData(userId))
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