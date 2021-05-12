package com.example.androidinternshippart3.firsttest.secondquestion

import android.graphics.Color
import android.graphics.drawable.Drawable
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
import com.example.androidinternshippart3.databinding.FragmentSecondQuestionBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first_question.*
import kotlinx.android.synthetic.main.fragment_second_question.*
import java.io.FileNotFoundException
import java.io.IOException

class SecondQuestion : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSecondQuestionBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_second_question, container, false
        )

        val userId = arguments?.getString("number")!!.toInt()

        val application = requireNotNull(this.activity).application

        val dataSourceQuestions = DataBase.getInstance(application).questionsDao
        val dataSourceAnswers = DataBase.getInstance(application).answersDao
        val dataSourceResults = DataBase.getInstance(application).resultsDao

        val viewModelFactory = SecondQuestionFragmentFactory(
            application,
            dataSourceQuestions,
            dataSourceAnswers,
            userId,
            dataSourceResults
        )

        val secondQuestionViewModel =
            ViewModelProvider(this, viewModelFactory).get(SecondQuestionViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.secondQuestionViewModel = secondQuestionViewModel

        secondQuestionViewModel.setImageEvent.observe(viewLifecycleOwner) {
            imageViewQuestion2.setImageDrawable(getDrawable("question2.jpg"))
        }
        secondQuestionViewModel.firstButtonEvent.observe(viewLifecycleOwner) {
            secondQuestionViewModel.secondQuestion(true)
            findNavController().navigate(R.id.thirdQuestionFragment, sendData(userId))
        }
        secondQuestionViewModel.secondButtonEvent.observe(viewLifecycleOwner) {
            secondQuestionViewModel.secondQuestion(false)
            findNavController().navigate(R.id.thirdQuestionFragment, sendData(userId))
        }
        secondQuestionViewModel.thirdButtonEvent.observe(viewLifecycleOwner) {
            secondQuestionViewModel.secondQuestion(false)
            findNavController().navigate(R.id.thirdQuestionFragment, sendData(userId))
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideOtherFragments()
    }

    private fun sendData(int: Int): Bundle {
        val bundle = Bundle()
        bundle.putString("number", int.toString())
        return bundle
    }

    private fun hideOtherFragments() {
        fragment.view?.setBackgroundColor(Color.WHITE);
    }
}