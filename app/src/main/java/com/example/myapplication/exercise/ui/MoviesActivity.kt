package com.example.myapplication.exercise.ui

import android.content.Context
import android.content.SharedPreferences
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.exercise.db.adapter.ListMovieAdapter
import kotlinx.android.synthetic.main.activity_movies.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesActivity : BaseActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {
    private var listMovies: MutableList<String> = arrayListOf()
    private lateinit var listMovieAdapter: ListMovieAdapter
    private lateinit var listCategories: MutableList<String>
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    var positionSpinner: Int = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_movies
    }

    override fun doViewCreated() {
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)
        initAdapter()
        dataSpinner()
        readData()
        initListener()
    }

    private fun readData() {
        val keywordSearch = sharedPreferences.getString("searchMovie", "")
        etSearchMovie.setText(keywordSearch)
    }

    private fun initListener() {
        btnSearch.setOnClickListener(this)
    }

    private fun dataSpinner() {
        listCategories = initMyRoomDB().getDAOCategories.getAllCategories()
        listCategories.add(0, "Select Categories")
        val arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listCategories)
        spinnerSelectCategories.adapter = arrayAdapter
    }

    private fun initAdapter() {
        listMovieAdapter = ListMovieAdapter(listMovies)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcvListResult.layoutManager = linearLayoutManager
        rcvListResult.setHasFixedSize(true)
        rcvListResult.adapter = listMovieAdapter
        spinnerSelectCategories.onItemSelectedListener = this
    }

    private fun searchMovie() {
        if (listMovies.size > 0) {
            listMovies.clear()
        }
        saveData()
        if (etSearchMovie.text.toString().isEmpty() && positionSpinner == 0) {
            CoroutineScope(Dispatchers.IO).launch {
                dataAllMovies()
            }
        } else if (etSearchMovie.text.toString()
                .isEmpty() && positionSpinner != 0
        ) {
            CoroutineScope(Dispatchers.IO).launch {
                dataMovieByNameCategories()
            }
        } else if (etSearchMovie.text.toString()
                .isNotEmpty() && positionSpinner != 0
        ) {
            CoroutineScope(Dispatchers.IO).launch {
                val movieName = etSearchMovie.text.toString()
                dataMovieByNameMovie(movieName, listCategories[positionSpinner])
                withContext(Dispatchers.Main) {
                    etSearchMovie.setText("")
                }
            }
        }
        listMovieAdapter.notifyDataSetChanged()
    }

    private fun saveData() {
        editor = sharedPreferences.edit()
        editor.putString("searchMovie", etSearchMovie.text.toString())
        editor.apply()
    }

    private fun dataAllMovies() {
        val list = initMyRoomDB().getDAOMovies.getAllMovie()
        listMovies.addAll(list.map {
            it.moviesName
        })
        listMovies.sortBy {
            it
        }
    }

    private fun dataMovieByNameCategories() {
        listMovies.addAll(initMyRoomDB().getDAOMovies.getMoviesByCategories(listCategories[positionSpinner]))
    }

    private fun dataMovieByNameMovie(nameMovie: String, nameCategories: String) {
        val search = "%$nameMovie%"
        listMovies.addAll(initMyRoomDB().getDAOMovies.getMoviesByNameMovie(search, nameCategories))
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnSearch -> searchMovie()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        positionSpinner = position
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}