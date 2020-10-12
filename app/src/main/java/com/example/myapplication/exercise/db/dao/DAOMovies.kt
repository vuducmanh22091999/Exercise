package com.example.myapplication.exercise.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DAOMovies {
    @Insert
    fun insertMovies(roomMovies: RoomMovies)

    @Query("Select moviesName from movies")
    fun getAllMovies(): MutableList<String>

    @Query("Select * from movies")
    fun getAllMovie(): List<RoomMovies>

    @Query("Select moviesName from movies where nameCategories LIKE :nameCategories")
    fun getMoviesByCategories(nameCategories: String): MutableList<String>

    @Query("Select moviesName from movies where moviesName LIKE :movieName and nameCategories LIKE :nameCategories")
    fun getMoviesByNameMovie(movieName: String, nameCategories: String): MutableList<String>
}