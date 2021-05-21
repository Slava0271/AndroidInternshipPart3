package com.example.androidinternshippart3.score.portal

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
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.databinding.FragmentPortalBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_portal.*


class PortalFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentPortalBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_portal, container, false
        )

        val application = requireNotNull(this.activity).application
        val args = arguments
        val userId = PortalFragmentArgs.fromBundle(args!!).UserId
        Log.d("Portal", userId.toString())

        val viewModelFactory = PortalFragmentFactory(application, userId)
        val portalViewModel = ViewModelProvider(this, viewModelFactory).get(PortalViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.portalViewModel = portalViewModel

        portalViewModel.buttonResultEvent.observe(viewLifecycleOwner, ::navigate)
        portalViewModel.buttonExitEvent.observe(viewLifecycleOwner, ::navigate)

        return binding.root
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}