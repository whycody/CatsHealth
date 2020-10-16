package com.whycody.catshealth.symptoms.recycler

interface SymptomRowView {

    fun setSymptomName(name: String)

    fun setSymptomChecked(checked: Boolean)

    fun setUnderlineVisibility(visibility: Int)
}