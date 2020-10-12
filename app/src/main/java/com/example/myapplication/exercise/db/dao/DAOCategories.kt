package com.example.myapplication.exercise.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DAOCategories {
    @Insert
    fun insertCategories(roomCategories: RoomCategories)

    @Query ("Select categoriesName from categories")
    fun getAllCategories(): MutableList<String>
}
