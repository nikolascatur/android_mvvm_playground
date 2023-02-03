package com.ruangguru.mvvmplaygroundarena

import android.app.Application
import com.ruangguru.mvvmplaygroundarena.data.local.word.WordRepository
import com.ruangguru.mvvmplaygroundarena.data.local.word.WordRoomDatabase
import com.ruangguru.mvvmplaygroundarena.module.localSaveModule
import com.ruangguru.mvvmplaygroundarena.module.networkModule
import com.ruangguru.mvvmplaygroundarena.module.repositoryModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WordApplication : Application() {

//    val applicationScope = CoroutineScope(SupervisorJob())
//
//    val database by lazy {
//        WordRoomDatabase.getDatabase(this, applicationScope)
//    }
//
//    val repository by lazy {
//        WordRepository(database.wordDao())
//    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WordApplication)
            modules(networkModule)
            modules(repositoryModule)
            modules(localSaveModule)
        }
    }

}