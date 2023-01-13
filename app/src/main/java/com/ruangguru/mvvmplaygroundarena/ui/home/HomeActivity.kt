package com.ruangguru.mvvmplaygroundarena.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ruangguru.mvvmplaygroundarena.MainActivity
import com.ruangguru.mvvmplaygroundarena.R
import com.ruangguru.mvvmplaygroundarena.core.common.ComparableItem
import com.ruangguru.mvvmplaygroundarena.core.common.GenericAdapter

class HomeActivity : AppCompatActivity() {


    private val menu = arrayListOf(
        HomeMenu(MENU_TEXT)
    )

    private val menuAdapter by lazy {
        GenericAdapter<HomeMenu>(
            ComparableItem(),
            R.layout.item_menu,
            { homeMenu, view ->
                val textView = view.findViewById<TextView>(R.id.title_text)
                textView.text = homeMenu.title
            },
            { homeMenu, view, position ->
                actionListener(homeMenu, view)
            }
        )
    }

    private fun actionListener(homeMenu: HomeMenu, view: View) {
        view.setOnClickListener {
            when(homeMenu.title) {
                MENU_TEXT -> {
                    startActivity(Intent(this@HomeActivity, MainActivity::class.java))
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()
    }

    private fun initView() {
        val recyclerMenu = findViewById<RecyclerView>(R.id.recyclerview_menu)
        recyclerMenu.adapter = menuAdapter
        recyclerMenu.layoutManager = LinearLayoutManager(this)
        menuAdapter.setData(menu)
//        with(recyclerMenu) {
//            adapter = menuAdapter
//            layoutManager = LinearLayoutManager(this@HomeActivity)
//        }
//        menuAdapter.setData(menu)
    }


    companion object {
        const val MENU_TEXT = "text"
    }
}