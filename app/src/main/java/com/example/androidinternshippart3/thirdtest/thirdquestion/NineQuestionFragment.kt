package com.example.androidinternshippart3.thirdtest.thirdquestion

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
import com.example.androidinternshippart3.databinding.FragmentNineQuestionBinding
import com.example.androidinternshippart3.thirdtest.secondquestion.EighthQuestionFactory
import com.example.androidinternshippart3.thirdtest.secondquestion.EighthWQuestionViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_eighth_question.*
import kotlinx.android.synthetic.main.fragment_nine_question.*
import java.io.FileNotFoundException
import java.io.IOException


class NineQuestionFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentNineQuestionBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_nine_question, container, false
        )

        val userId = arguments?.getString("number")!!.toInt()

        val application = requireNotNull(this.activity).application

        val dataSourceQuestions = DataBase.getInstance(application).questionsDao
        val dataSourceAnswers = DataBase.getInstance(application).answersDao
        val dataSourceResults = DataBase.getInstance(application).resultsDao

        val viewModelFactory =
            NineQuestionFactory(
                application,
                dataSourceQuestions,
                dataSourceAnswers,
                userId,
                dataSourceResults
            )


        val eighthQuestionViewModel =
            ViewModelProvider(this, viewModelFactory).get(NineQuestionViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.nineQuestionViewModel = eighthQuestionViewModel


        eighthQuestionViewModel.setImageEvent.observe(viewLifecycleOwner) {
            imageViewQuestion9.setImageDrawable(getDrawable("question9.jpg"))
        }
        eighthQuestionViewModel.firstButtonEvent.observe(viewLifecycleOwner) {
            eighthQuestionViewModel.nineQuestion(true)
            findNavController().navigate(R.id.portalFragment, sendData(userId))
        }
        eighthQuestionViewModel.secondButtonEvent.observe(viewLifecycleOwner) {
            eighthQuestionViewModel.nineQuestion(false)
            findNavController().navigate(R.id.portalFragment, sendData(userId))
        }
        eighthQuestionViewModel.thirdButtonEvent.observe(viewLifecycleOwner) {
            eighthQuestionViewModel.nineQuestion(false)
            findNavController().navigate(R.id.portalFragment, sendData(userId))
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