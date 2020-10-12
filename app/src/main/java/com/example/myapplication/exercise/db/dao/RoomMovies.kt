package com.example.myapplication.exercise.db.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class RoomMovies(
    @PrimaryKey(autoGenerate = true)
    var idMovies : Int = 0,

    val moviesName : String,
    val nameCategories : String
)
