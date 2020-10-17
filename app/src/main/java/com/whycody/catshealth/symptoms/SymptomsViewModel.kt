package com.whycody.catshealth.symptoms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.whycody.catshealth.data.Symptom
import com.whycody.catshealth.data.SymptomItem
import com.whycody.catshealth.data.symptom.SymptomRepository
import com.whycody.catshealth.symptoms.recycler.SymptomAdapter

class SymptomsViewModel(symptomRepository: SymptomRepository): ViewModel(), SymptomClickListener {

    private val symptomsList = symptomRepository.getAllSymptoms()
    private val symptomsItemsList: MutableLiveData<List<SymptomItem>> = MutableLiveData()
    val adapter = SymptomAdapter(this)

    init {
        symptomsList.observeForever {
            val symptomItems = mutableListOf<SymptomItem>()
            if(symptomsItemsList.value.isNullOrEmpty()) {
                for(symptom in it)
                    symptomItems.add(SymptomItem(symptom))
                symptomsItemsList.value = symptomItems
            }
        }
    }

    fun getAllSymptoms(): LiveData<List<Symptom>> {
        return symptomsList
    }

    fun getAllSymptomsItems(): LiveData<List<SymptomItem>> {
        return symptomsItemsList
    }

    override fun onClick(symptomItem: SymptomItem, position: Int) {
        symptomsItemsList.value!![position].checked = !symptomItem.checked
    }
}