package com.heske.test.room_relationships

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.heske.test.room_relationships.database.UniversityDatabase

/**
 * See
 *   https://androidessence.com/room-relationship-recap
 */
class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    val dao = UniversityDatabase.getDatabase(this).universityDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            AppViewModelFactory
        ).get(MainViewModel::class.java)

        setupObservers()

        viewModel.addData(this)
    }

    private fun setupObservers() {
        viewModel.studentsWithVehicles.observe(
            this, { list ->
                if (list.isNotEmpty())
                    Log.d("", "List contains ${list.size} items")
            }
        )
        viewModel.studentsWithCourses.observe(
            this, { list ->
                if (!list.isEmpty())
                    Log.d("", "List contains ${list.size} items")
            }
        )
        viewModel.coursesWithStudents.observe(
            this, { list ->
                if (list.isNotEmpty())
                    Log.d("", "List contains ${list.size} items")
            }
        )
    }
}