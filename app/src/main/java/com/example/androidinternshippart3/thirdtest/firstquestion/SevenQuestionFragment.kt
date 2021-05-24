package com.example.androidinternshippart3.thirdtest.firstquestion

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
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.database.DataBase
import com.example.androidinternshippart3.databinding.FragmentSevenQuestionBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_seven_question.*
import kotlinx.android.synthetic.main.fragment_six_question.*
import java.io.FileNotFoundException
import java.io.IOException


class SevenQuestionFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentSevenQuestionBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_seven_question, container, false
        )

        val args = arguments
        val userId = SevenQuestionFragmentArgs.fromBundle(args!!).userId

        val application = requireNotNull(this.activity).application

        val dataSourceQuestions = DataBase.getInstance(application).questionsDao
        val dataSourceAnswers = DataBase.getInstance(application).answersDao
        val dataSourceResults = DataBase.getInstance(application).resultsDao

        val viewModelFactory =
                SevenQuestionFactory(
                        application,
                        dataSourceQuestions,
                        dataSourceAnswers,
                        userId,
                        dataSourceResults
                )

        val sevenQuestionViewModel =
                ViewModelProvider(this, viewModelFactory).get(SevenQuestionViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.sevenQuestionViewModel = sevenQuestionViewModel


        sevenQuestionViewModel.setImageEvent.observe(viewLifecycleOwner) {
            imageViewQuestion7.setImageDrawable(getDrawable("question7.jpg"))
        }
        sevenQuestionViewModel.firstButtonEvent.observe(viewLifecycleOwner) {
            sevenQuestionViewModel.sevenQuestion(true)
            navigate(it)
        }
        sevenQuestionViewModel.secondButtonEvent.observe(viewLifecycleOwner) {
            sevenQuestionViewModel.sevenQuestion(false)
            navigate(it)
        }
        sevenQuestionViewModel.thirdButtonEvent.observe(viewLifecycleOwner) {
            sevenQuestionViewModel.sevenQuestion(false)
            navigate(it)
        }
        sevenQuestionViewModel.backButtonEvent.observe(viewLifecycleOwner,::navigate)

        return binding.root
    }


    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
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
}