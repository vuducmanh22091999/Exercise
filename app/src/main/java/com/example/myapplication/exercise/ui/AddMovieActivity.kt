package com.example.myapplication.exercise.ui

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.exercise.db.dao.RoomMovies
import kotlinx.android.synthetic.main.activity_add_movie.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddMovieActivity : BaseActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {
    private lateinit var listCategories: MutableList<String>
    var int : Int = 0
    override fun getLayoutId(): Int {
        return R.layout.activity_add_movie
    }

    override fun doViewCreated() {
        initListener()
        getCategories()
    }

    private fun initListener() {
        btnAddMovieAndCategories.setOnClickListener(this)
        btnFinish.setOnClickListener(this)
    }

    private fun getCategories() {
        listCategories = initMyRoomDB().getDAOCategories.getAllCategories()
        listCategories.add(0, "Select Categories")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listCategories)
        spinnerSelectMovieCategories.adapter = arrayAdapter
        spinnerSelectMovieCategories.onItemSelectedListener = this
    }

    private fun addMovieAndCategories() {
        val nameMovie = etMovieName.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            initMyRoomDB().getDAOMovies.insertMovies(
                RoomMovies(
                    moviesName = nameMovie,
                    nameCategories = listCategories[int]
                )
            )
            withContext(Dispatchers.Main) {
                etMovieName.setText("")
                Toast.makeText(this@AddMovieActivity, "Success!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun finishFeatures() {
        val intentFeatures = Intent(this, FeaturesActivity::class.java)
        startActivity(intentFeatures)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnAddMovieAndCategories -> addMovieAndCategories()
            R.id.btnFinish -> finishFeatures()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        int = position
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}