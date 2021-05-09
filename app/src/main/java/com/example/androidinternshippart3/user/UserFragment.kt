package com.example.androidinternshippart3.user

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
        val viewModelFactory = UserFragmentFactory(application)
        val userViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)



        binding.lifecycleOwner = viewLifecycleOwner
        binding.userViewModel = userViewModel

        userViewModel.navigateToLoginEvent.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.loginFragment)
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