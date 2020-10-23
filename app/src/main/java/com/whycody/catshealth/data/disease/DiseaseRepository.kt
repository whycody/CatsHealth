package com.whycody.catshealth.data.disease

import com.whycody.catshealth.data.Disease
import com.whycody.catshealth.data.DiseaseResult

class DiseaseRepository(private val diseaseDao: DiseaseDao) {

    fun getDisease(id: Int) = diseaseDao.getDisease(id)

    fun getAllDiseases() = diseaseDao.getAllDiseases()

    fun getDiseasesWithSymptoms(symptoms: List<Int>): List<DiseaseResult> {
        return diseaseDao.getAllDiseases().filter { disease ->
            disease.symptomsIds.any { symptoms.contains(it) }
        }.sortedByDescending {  disease -> disease.priority
        }.map { disease -> getDiseaseResult(disease, symptoms)
        }.sortedBy { disease -> disease.symptomsNeeds }
    }

    private fun getDiseaseResult(disease: Disease, symptoms: List<Int>): DiseaseResult {
        val symptomsContains = disease.symptomsIds.count { symptomId -> symptoms.contains(symptomId) }
        val symptomsNeeded = disease.symptomsIds.size - symptomsContains
        return DiseaseResult(disease.id, disease.symptomsIds, symptomsContains, symptomsNeeded)
    }
}