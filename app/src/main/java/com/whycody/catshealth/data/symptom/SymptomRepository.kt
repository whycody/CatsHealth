package com.whycody.catshealth.data.symptom

class SymptomRepository(private val symptomDao: SymptomDao) {

    fun getSymptom(id: Int) = symptomDao.getSymptom(id)

    fun flowAllSymptoms() = symptomDao.flowAllSymptoms()
}