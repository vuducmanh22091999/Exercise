package com.example.myapplication.exercise.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.exercise.db.dao.RoomCategories
import kotlinx.android.synthetic.main.activity_add_categories.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddCategoriesActivity : BaseActivity(), View.OnClickListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_add_categories
    }

    override fun doViewCreated() {
        initListener()
    }

    private fun initListener() {
        btnAddMovie.setOnClickListener(this)
        btnAddMovieCategories.setOnClickListener(this)
    }

    private fun addMovie() {
        val intentAddMovie = Intent(this, AddMovieActivity::class.java)
        startActivity(intentAddMovie)
    }

    @SuppressLint("ShowToast")
    private fun addMovieCategories() {
        if (etMovieCategories.text.toString().isEmpty()) {
            Toast.makeText(this, "Don't leave blank", Toast.LENGTH_SHORT)
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                initMyRoomDB().getDAOCategories.let {
                    val movieName = etMovieCategories.text.toString()
                    it.insertCategories(RoomCategories(categoriesName = movieName))
                    etMovieCategories.setText("")
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@AddCategoriesActivity, "Success!!!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnAddMovie -> addMovie()
            R.id.btnAddMovieCategories -> addMovieCategories()
        }
    }
}