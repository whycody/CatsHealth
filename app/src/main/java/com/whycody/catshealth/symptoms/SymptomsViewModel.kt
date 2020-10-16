package com.whycody.catshealth.symptoms

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.whycody.catshealth.data.Symptom
import com.whycody.catshealth.data.symptom.SymptomRepository

class SymptomsViewModel(symptomRepository: SymptomRepository): ViewModel() {

    private val symptomsList: LiveData<List<Symptom>> = symptomRepository.getAllSymptoms()

    fun getAllSymptoms(): LiveData<List<Symptom>> {
        return symptomsList
    }
}