package com.example.myapplication.exercise.db.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.VERSION_DB

@Database(entities = [RoomMovies::class, RoomCategories::class], version = 1, exportSchema = false)
abstract class MyRoomDB : RoomDatabase() {
    abstract val getDAOMovies: DAOMovies
    abstract val getDAOCategories: DAOCategories

}