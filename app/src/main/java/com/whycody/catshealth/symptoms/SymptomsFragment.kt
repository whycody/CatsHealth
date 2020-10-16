package com.whycody.catshealth.symptoms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.whycody.catshealth.MainActivity
import com.whycody.catshealth.R
import com.whycody.catshealth.symptoms.recycler.SymptomAdapter
import kotlinx.android.synthetic.main.fragment_symptoms.view.*
import org.koin.android.ext.android.inject

class SymptomsFragment : Fragment() {

    private val symptomsViewModel: SymptomsViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_symptoms, container, false)
        view.symptomsRecycler.layoutManager = LinearLayoutManager(activity?.applicationContext)
        val adapter = SymptomAdapter()
        view.symptomsRecycler.adapter = adapter
        symptomsViewModel.getAllSymptoms().observe(activity as MainActivity, {
            adapter.setSymptoms(it)
        })
        return view
    }

}