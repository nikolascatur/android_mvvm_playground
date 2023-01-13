package com.ruangguru.mvvmplaygroundarena.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.ruangguru.mvvmplaygroundarena.data.local.word.Word
import com.ruangguru.mvvmplaygroundarena.data.local.word.WordDao
import com.ruangguru.mvvmplaygroundarena.data.local.word.WordRoomDatabase
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class WordRoomDatabaseTest : TestCase() {


    private lateinit var db: WordRoomDatabase
    private lateinit var dao: WordDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, WordRoomDatabase::class.java).build()
        dao = db.wordDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertTest() = runBlocking {
        val word = Word(0, "nikoo")
        val word2 = Word(0, "nikoolas")
        dao.insert(word)
        dao.insert(word2)
        dao.getAlphabetizeWords().test {
            val list = awaitItem()
            list.forEach { word ->
                println("WORD id ${word.id} => ${word.word}")
            }
            assert(list.contains(word))
            cancel()
        }
//        allWord.observerValue
//        assertEquals(allWord.observerValue.first().contains(word), true)
    }

//    fun <T> LiveData<T>.test() = LiveDataTestObserver<T>().also { observeForever(it) }
//
//    class LiveDataTestObserver<T> : Observer<T> {
//        val observerValue = mutableListOf<T>()
//        override fun onChanged(t: T) {
//            observerValue.add(t)
//        }
//
//    }
}