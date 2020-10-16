package com.whycody.catshealth.symptoms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.whycody.catshealth.R
import kotlinx.android.synthetic.main.fragment_symptoms.view.*

class SymptomsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_symptoms, container, false)
        view.symptomsRecycler.layoutManager = LinearLayoutManager(activity?.applicationContext)
        return view
    }

}