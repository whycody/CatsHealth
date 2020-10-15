package com.whycody.catshealth.data

import android.app.Application
import androidx.room.Room
import com.whycody.catshealth.data.disease.DiseaseDao
import com.whycody.catshealth.data.disease.DiseaseRepository
import com.whycody.catshealth.data.symptom.SymptomDao
import com.whycody.catshealth.data.symptom.SymptomRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    fun provideDatabase(application: Application): MyDatabase {
        return Room.databaseBuilder(application, MyDatabase::class.java, "MyDatabase")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("symptoms.db")
            .build()
    }

    fun provideSymptomDao(database: MyDatabase): SymptomDao {
        return  database.symptomDao()
    }

    fun provideDiseaseDao(database: MyDatabase): DiseaseDao {
        return  database.diseaseDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideSymptomDao(get()) }
    single { provideDiseaseDao(get()) }
}

val repositoryModule = module {
    single { SymptomRepository(get()) }
    single { DiseaseRepository(get()) }
}