package com.whycody.catshealth.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.whycody.catshealth.Converters
import com.whycody.catshealth.data.disease.DiseaseDao
import com.whycody.catshealth.data.symptom.SymptomDao

@Database(entities = [Disease::class, Symptom::class], version = 1)
@TypeConverters(Converters::class)
public abstract class MyDatabase: RoomDatabase() {

    abstract fun diseaseDao(): DiseaseDao
    abstract fun symptomDao(): SymptomDao


}
