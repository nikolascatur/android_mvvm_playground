package com.ruangguru.mvvmplaygroundarena.data.local

import androidx.annotation.WorkerThread
import com.ruangguru.mvvmplaygroundarena.room.Word
import com.ruangguru.mvvmplaygroundarena.room.WordDao
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizeWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}