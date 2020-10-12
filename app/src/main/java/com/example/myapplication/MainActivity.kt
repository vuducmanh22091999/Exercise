package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.myapplication.exercise.db.dao.MyRoomDB

class MainActivity : AppCompatActivity() {
    private lateinit var myRoomDB: MyRoomDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMyRoomDB()
    }

    private fun initMyRoomDB() : MyRoomDB {
        if (!this::myRoomDB.isInitialized) {
            myRoomDB = Room.databaseBuilder(applicationContext, MyRoomDB::class.java, "exercise")
                .allowMainThreadQueries()
                .build()
        }
        return myRoomDB
    }
}