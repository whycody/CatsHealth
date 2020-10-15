package com.whycody.catshealth.data.symptom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.whycody.catshealth.data.Symptom

@Dao
interface SymptomDao {

    @Query("SELECT * FROM symptoms WHERE id=:id LIMIT 1")
    fun getSymptom(id: Int): Symptom

    @Query("SELECT * FROM symptoms")
    fun getAllSymptoms(): LiveData<List<Symptom>>
}