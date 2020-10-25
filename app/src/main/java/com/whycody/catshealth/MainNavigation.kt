package com.whycody.catshealth

import androidx.fragment.app.Fragment

interface MainNavigation {

    fun navigateTo(fragment: Fragment, addToBackstack: Boolean = false, tag: String? = null)

    fun removeFragmentFromBackStack(tag: String)
}