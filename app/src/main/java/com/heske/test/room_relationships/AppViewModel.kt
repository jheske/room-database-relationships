package com.heske.test.room_relationships

import androidx.lifecycle.ViewModel
import com.heske.test.room_relationships.database.Repository

open class AppViewModel(protected val repository: Repository) : ViewModel() {
    protected val dataRepository = repository
}