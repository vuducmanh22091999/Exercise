package com.example.myapplication.exercise.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_features.*

class FeaturesActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_features)

        initListener()
    }

    private fun initListener() {
        btnAdd.setOnClickListener(this)
        btnShow.setOnClickListener(this)
    }

    private fun addCategories() {
        val intentAddCategories = Intent(this, AddCategoriesActivity::class.java)
        startActivity(intentAddCategories)
    }

    private fun showMovies() {
        val intentShowMovies = Intent(this, MoviesActivity::class.java)
        startActivity(intentShowMovies)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnAdd -> addCategories()
            R.id.btnShow -> showMovies()
        }
    }
}