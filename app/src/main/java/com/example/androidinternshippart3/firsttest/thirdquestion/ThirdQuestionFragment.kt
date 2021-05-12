package com.example.androidinternshippart3.firsttest.thirdquestion

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.database.DataBase
import com.example.androidinternshippart3.databinding.FragmentThirdQuestionBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_third_question.*
import java.io.FileNotFoundException
import java.io.IOException


class ThirdQuestionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentThirdQuestionBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_third_question, container, false
        )

        val userId = arguments?.getString("number")!!.toInt()

        val application = requireNotNull(this.activity).application

        val dataSourceQuestions = DataBase.getInstance(application).questionsDao
        val dataSourceAnswers = DataBase.getInstance(application).answersDao
        val dataSourceResults = DataBase.getInstance(application).resultsDao

        val viewModelFactory = ThirdQuestionFactory(
            application,
            dataSourceQuestions,
            dataSourceAnswers,
            userId,
            dataSourceResults
        )
        val thirdQuestionViewModel =
            ViewModelProvider(this, viewModelFactory).get(ThirdQuestionViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.thirdQuestionViewModel = thirdQuestionViewModel

        thirdQuestionViewModel.setImageEvent.observe(viewLifecycleOwner) {
            imageViewQuestion3.setImageDrawable(getDrawable("question3.jpg"))
        }
        thirdQuestionViewModel.firstButtonEvent.observe(viewLifecycleOwner) {
            thirdQuestionViewModel.thirdQuestion(false)
            findNavController().navigate(R.id.portalFragment, sendData(userId))
        }
        thirdQuestionViewModel.secondButtonEvent.observe(viewLifecycleOwner) {
            thirdQuestionViewModel.thirdQuestion(false)
            findNavController().navigate(R.id.portalFragment, sendData(userId))
        }
        thirdQuestionViewModel.thirdButtonEvent.observe(viewLifecycleOwner) {
            thirdQuestionViewModel.thirdQuestion(true)
            findNavController().navigate(R.id.portalFragment, sendData(userId))
        }

        return binding.root
    }

    private fun getDrawable(name: String): Drawable {
        val resource =
            try {
                context?.assets?.open(name)
            } catch (exc: IOException) {
                throw FileNotFoundException("No such file at $name")
            }
        return Drawable.createFromStream(resource, null)
            ?: throw Exception("Can't convert file $name to drawable")
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