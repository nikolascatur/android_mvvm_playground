package com.ruangguru.mvvmplaygroundarena

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ruangguru.mvvmplaygroundarena.data.local.word.Word
import com.ruangguru.mvvmplaygroundarena.ui.new.NewWordActivity
import com.ruangguru.mvvmplaygroundarena.ui.word.WordListAdapter
import com.ruangguru.mvvmplaygroundarena.ui.word.WordViewModel
import com.ruangguru.mvvmplaygroundarena.ui.word.WordViewModelFactory

class MainActivity : AppCompatActivity() {

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordApplication).repository)
    }

    private val adapter by lazy {
        WordListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
        subscribeWord()
    }

    private fun subscribeWord() {
        wordViewModel.allWords.observe(this) { words ->
            Log.d("nikoo","LENGTH WORDD ${words.size}")
            words.let {
                adapter.submitList(it)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("nikoo","CHECK $requestCode == $newWordActivityRequestCode resultCode $resultCode " +
            "activityResult ${Activity.RESULT_OK}")
        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let {
                Log.d("nikoo","SHOW STRING EXTRAAA  => $it")
                val word = Word(it)
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        const val newWordActivityRequestCode = 1
    }
}