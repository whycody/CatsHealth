package com.whycody.catshealth.data.disease

class DiseaseRepository(private val diseaseDao: DiseaseDao) {

    fun getDisease(id: Int) = diseaseDao.getDisease(id)

    fun getAllDiseases() = diseaseDao.getAllDiseases()

    fun getDiseasesWithSymptoms(symptoms: List<Int>) =
        diseaseDao.getDiseasesWithSymptoms(symptoms)
}