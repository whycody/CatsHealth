package com.whycody.catshealth.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.whycody.catshealth.MainNavigation
import com.whycody.catshealth.R
import com.whycody.catshealth.databinding.FragmentResultBinding
import kotlinx.android.synthetic.main.fragment_result.view.*
import org.koin.android.ext.android.inject

class ResultFragment : Fragment() {

    private val viewModel: ResultViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentResultBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_result, container, false)
        binding.resultViewModel = viewModel
        binding.lifecycleOwner = activity
        (activity as MainNavigation).removeFragmentFromBackStack("QuestionFragment")
        return binding.root
    }
}