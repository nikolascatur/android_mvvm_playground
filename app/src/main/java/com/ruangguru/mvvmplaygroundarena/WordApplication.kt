package com.ruangguru.mvvmplaygroundarena

import android.app.Application
import com.ruangguru.mvvmplaygroundarena.data.local.WordRepository
import com.ruangguru.mvvmplaygroundarena.room.WordRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {
        WordRoomDatabase.getDatabase(this, applicationScope)
    }

    val repository by lazy {
        WordRepository(database.wordDao())
    }

}