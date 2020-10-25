package com.whycody.catshealth.result

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.whycody.catshealth.data.SearchResult
import com.whycody.catshealth.data.disease.DiseaseRepository
import com.whycody.catshealth.data.symptom.SymptomRepository
import java.lang.StringBuilder
import java.util.*

class ResultViewModel(private val searchResult: SearchResult,
                    private val symptomRepository: SymptomRepository,
                    private val diseaseRepository: DiseaseRepository) : ViewModel() {

    var probableDiseaseExists: MutableLiveData<Boolean> = MutableLiveData(searchResult.probableDiseaseId != null)
    val possibleDiseases: MutableLiveData<String> = MutableLiveData(getSymptomsInText())

    private fun getSymptomsInText(): String {
        val allSymptomsBuilder = StringBuilder()
        searchResult.symptomsIds.forEachIndexed { index, id ->
            if(id != searchResult.probableDiseaseId) addSymptomToString(allSymptomsBuilder, index, id)
        }
        return allSymptomsBuilder.toString()
    }

    private fun addSymptomToString(allSymptoms: StringBuilder, index: Int, id: Int) {
        allSymptoms.append(symptomRepository.getSymptom(id).name.toLowerCase(Locale.ROOT))
        if(index != searchResult.symptomsIds.size - 1) allSymptoms.append(", ")
    }
}