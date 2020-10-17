package com.whycody.catshealth.symptoms

import com.whycody.catshealth.data.SymptomItem

interface SymptomClickListener {

    fun onClick(symptomItem: SymptomItem, position: Int)
}