package com.whycody.catshealth.data.disease

import com.whycody.catshealth.data.disease.DiseaseDao

class DiseaseRepository(private val diseaseDao: DiseaseDao) {

    fun getDisease(id: Int) = diseaseDao.getDisease(id)

    fun getAllDiseases() = diseaseDao.getAllDiseases()

    fun getDiseasesWithSymptoms(symptoms: List<Int>) =
        diseaseDao.getDiseasesWithSymptoms(symptoms)
}