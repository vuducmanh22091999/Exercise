package com.example.myapplication.exercise.db.dao

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "categories")
data class RoomCategories(
    @NotNull
    @PrimaryKey(autoGenerate = true)
    var idCategories : Int = 0,
    var categoriesName : String? = null
)
