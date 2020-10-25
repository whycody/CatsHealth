package com.whycody.catshealth.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.whycody.catshealth.data.Disease
import com.whycody.catshealth.data.SearchResult
import com.whycody.catshealth.data.disease.DiseaseRepository
import com.whycody.catshealth.data.symptom.SymptomRepository
import java.lang.StringBuilder
import java.util.*

class ResultViewModel(private val searchResult: SearchResult,
                    private val symptomRepository: SymptomRepository,
                    private val diseaseRepository: DiseaseRepository) : ViewModel() {

    val probableDiseaseExists: MutableLiveData<Boolean> = MutableLiveData(searchResult.probableDiseaseId != null)
    val symptoms: MutableLiveData<String> = MutableLiveData(getSymptomsInText())
    val possibleDiseases: MutableLiveData<List<Disease>> = MutableLiveData(getDiseases())

    private fun getSymptomsInText(): String {
        val allSymptomsBuilder = StringBuilder()
        searchResult.symptomsIds.forEachIndexed { index, id ->
            addSymptomToString(allSymptomsBuilder, index, id)
        }
        return allSymptomsBuilder.toString()
    }

    private fun addSymptomToString(allSymptoms: StringBuilder, index: Int, id: Int) {
        allSymptoms.append(symptomRepository.getSymptom(id).name.toLowerCase(Locale.ROOT))
        if(index != searchResult.symptomsIds.size - 1) allSymptoms.append(", ")
    }

    private fun getDiseases(): List<Disease> {
        val diseasesList = mutableListOf<Disease>()
        searchResult.possibleDiseases.forEach {
            if(it.id != searchResult.probableDiseaseId)
                diseasesList.add(diseaseRepository.getDisease(it.id))
        }
        return diseasesList.toList()
    }

    fun getProbableDisease() =
        if(searchResult.probableDiseaseId == null) null
        else diseaseRepository.getDisease(searchResult.probableDiseaseId!!)
}