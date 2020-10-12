package com.example.myapplication.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.myapplication.exercise.db.dao.MyRoomDB

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var myRoomDB: MyRoomDB

    abstract fun getLayoutId() : Int
    abstract fun doViewCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        doViewCreated()
    }

    fun addFragment(fragment: Fragment, id : Int) {
        supportFragmentManager.beginTransaction()
            .add(id, fragment, fragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }

    fun replaceFragment (fragment: Fragment, id : Int) {
        supportFragmentManager.beginTransaction()
            .replace(id, fragment, fragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }

    fun initMyRoomDB() : MyRoomDB {
        if (!this::myRoomDB.isInitialized) {
            myRoomDB = Room.databaseBuilder(applicationContext, MyRoomDB::class.java, "exercise")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
        return myRoomDB
    }
}