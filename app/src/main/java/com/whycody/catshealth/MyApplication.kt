package com.whycody.catshealth

import android.app.Application
import com.whycody.catshealth.data.dataModule
import com.whycody.catshealth.data.repositoryModule
import com.whycody.catshealth.data.utilModule
import com.whycody.catshealth.data.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(dataModule, repositoryModule, utilModule, viewModelModule)
        }
    }
}

