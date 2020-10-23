package com.whycody.catshealth.data.disease

import androidx.room.Dao
import androidx.room.Query
import com.whycody.catshealth.data.Disease

@Dao
interface DiseaseDao {

    @Query("SELECT * FROM diseases WHERE id LIKE :id LIMIT 1")
    fun getDisease(id: Int): Disease

    @Query("SELECT * FROM diseases")
    fun getAllDiseases(): List<Disease>
}