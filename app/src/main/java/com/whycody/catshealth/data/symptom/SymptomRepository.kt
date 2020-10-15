package com.whycody.catshealth.data.symptom

import com.whycody.catshealth.data.symptom.SymptomDao


class SymptomRepository(private val symptomDao: SymptomDao) {

    fun getSymptom(id: Int) = symptomDao.getSymptom(id)

    fun getAllSymptoms() = symptomDao.getAllSymptoms()
}