package com.heske.test.room_relationships

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.heske.test.room_relationships.database.Repository

object AppViewModelFactory : ViewModelProvider.Factory {

    lateinit var dependencies: Repository

    fun inject(application: Application, dependencies: Repository) {
        AppViewModelFactory.dependencies = dependencies
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (ViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(Repository::class.java)
                .newInstance(dependencies)
        } else {
            throw IllegalStateException("ViewModel must extend AppViewModel")
        }
    }
}