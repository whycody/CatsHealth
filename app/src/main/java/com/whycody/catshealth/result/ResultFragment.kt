package com.whycody.catshealth.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.whycody.catshealth.MainActivity
import com.whycody.catshealth.MainNavigation
import com.whycody.catshealth.R
import com.whycody.catshealth.databinding.FragmentResultBinding
import com.whycody.catshealth.result.recycler.ResultAdapter
import org.koin.android.ext.android.inject

class ResultFragment : Fragment() {

    private val viewModel: ResultViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentResultBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_result, container, false)
        binding.resultViewModel = viewModel
        binding.disease = viewModel.getProbableDisease()
        binding.position = 0
        binding.lifecycleOwner = activity
        val resultAdapter = ResultAdapter()
        binding.possibleDiseasesRecycler.layoutManager = LinearLayoutManager(activity)
        binding.possibleDiseasesRecycler.adapter = resultAdapter
        observePossibleDiseases(resultAdapter)
        (activity as MainNavigation).removeFragmentFromBackStack("QuestionFragment")
        return binding.root
    }

    private fun observePossibleDiseases(resultAdapter: ResultAdapter) {
        viewModel.possibleDiseases.observe(activity as MainActivity, {
            resultAdapter.submitList(it)
        })
    }
}