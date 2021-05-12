package com.example.androidinternshippart3.secondtest.firstquestion

import android.graphics.Color
import android.graphics.drawable.Drawable
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
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.database.DataBase
import com.example.androidinternshippart3.databinding.FragmentFourhQuestionBinding
import com.example.androidinternshippart3.firsttest.firstquestion.FirstQuestionFragmentFactory
import com.example.androidinternshippart3.firsttest.firstquestion.FirstQuestionViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first_question.*
import kotlinx.android.synthetic.main.fragment_fourh_question.*
import java.io.FileNotFoundException
import java.io.IOException


class FourthQuestionFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFourhQuestionBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_fourh_question, container, false
        )
        val userId = arguments?.getString("number")!!.toInt()

        val application = requireNotNull(this.activity).application

        val dataSourceQuestions = DataBase.getInstance(application).questionsDao
        val dataSourceAnswers = DataBase.getInstance(application).answersDao
        val dataSourceResults = DataBase.getInstance(application).resultsDao

        val viewModelFactory =
            FourthQuestionFactory(
                application,
                dataSourceQuestions,
                dataSourceAnswers,
                userId,
                dataSourceResults
            )

        val fourthQuestionViewModel =
            ViewModelProvider(this, viewModelFactory).get(FourthQuestionViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.fourthQuestionViewModel = fourthQuestionViewModel
        fourthQuestionViewModel.setImageEvent.observe(viewLifecycleOwner) {
            imageViewQuestion4.setImageDrawable(getDrawable("question4.jpg"))
        }
        fourthQuestionViewModel.firstButtonEvent.observe(viewLifecycleOwner) {
            fourthQuestionViewModel.fourthQuestion(false)
            findNavController().navigate(R.id.fifthQuestionFragment,sendData(userId))
        }
        fourthQuestionViewModel.secondButtonEvent.observe(viewLifecycleOwner) {
            fourthQuestionViewModel.fourthQuestion(true)
            findNavController().navigate(R.id.fifthQuestionFragment,sendData(userId))
        }
        fourthQuestionViewModel.thirdButtonEvent.observe(viewLifecycleOwner) {
            fourthQuestionViewModel.fourthQuestion(false)
            findNavController().navigate(R.id.fifthQuestionFragment,sendData(userId))
        }

        return binding.root
    }

    private fun sendData(int: Int): Bundle {
        val bundle = Bundle()
        bundle.putString("number", int.toString())
        return bundle
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

    private fun hideOtherFragments() {
        fragment.view?.setBackgroundColor(Color.WHITE);
    }

}