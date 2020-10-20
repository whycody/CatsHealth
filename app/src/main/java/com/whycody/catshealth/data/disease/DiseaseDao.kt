package com.whycody.catshealth.data.disease

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.whycody.catshealth.data.Disease

@Dao
interface DiseaseDao {

    @Query("SELECT * FROM diseases WHERE id LIKE :id LIMIT 1")
    fun getDisease(id: Int): Disease

    @Query("SELECT * FROM diseases")
    fun getAllDiseases(): LiveData<List<Disease>>

    @Query("SELECT * FROM diseases WHERE symptoms_ids IN (:symptoms) ORDER BY priority")
    fun getDiseasesWithSymptoms(symptoms: List<Int>): LiveData<List<Disease>>
}