package com.whycody.catshealth.data.symptom

import androidx.room.Dao
import androidx.room.Query
import com.whycody.catshealth.data.Symptom
import kotlinx.coroutines.flow.Flow

@Dao
interface SymptomDao {

    @Query("SELECT * FROM symptoms WHERE id=:id LIMIT 1")
    fun getSymptom(id: Int): Symptom

    @Query("SELECT * FROM symptoms")
    fun flowAllSymptoms(): Flow<List<Symptom>>
}