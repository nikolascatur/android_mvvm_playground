package com.ruangguru.mvvmplaygroundarena.module

import com.ruangguru.mvvmplaygroundarena.data.local.word.WordRepository
import com.ruangguru.mvvmplaygroundarena.data.local.word.WordRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val localSaveModule = module {
    single {
        WordRoomDatabase.getDatabase(get(), CoroutineScope(SupervisorJob()))
    }

    single {
        WordRepository(get<WordRoomDatabase>().wordDao())
    }
}