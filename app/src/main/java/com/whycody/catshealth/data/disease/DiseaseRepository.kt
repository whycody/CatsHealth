package com.whycody.catshealth.data.disease

import com.whycody.catshealth.data.Disease
import com.whycody.catshealth.data.DiseaseResult

class DiseaseRepository(private val diseaseDao: DiseaseDao) {

    fun getDisease(id: Int) = diseaseDao.getDisease(id)

    fun getAllDiseases() = diseaseDao.getAllDiseases()

    fun getDiseasesWithSymptoms(symptoms: List<Int>): List<DiseaseResult> {
        return diseaseDao.getAllDiseases().filter { disease ->
            disease.symptomsIds.any { symptoms.contains(it) }
        }.map { disease -> getDiseaseResult(disease, symptoms)
        }.sortedWith(compareBy<DiseaseResult> {it.symptomsNeeds.size}.thenByDescending { it.priority })
    }

    private fun getDiseaseResult(disease: Disease, symptoms: List<Int>): DiseaseResult {
        val symptomsContains = disease.symptomsIds.filter { symptomId -> symptoms.contains(symptomId) }
        val symptomsNeeded = disease.symptomsIds.filter { symptomId -> !symptoms.contains(symptomId) }
        return DiseaseResult(disease.id, disease.priority, disease.symptomsIds, symptomsContains, symptomsNeeded)
    }
}