package com.whycody.catshealth.startup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.whycody.catshealth.R
import kotlinx.android.synthetic.main.fragment_startup.view.*

class StartupFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_startup, container, false)
        view.circleLogo.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.circle_logo_anim))
        return view
    }
}