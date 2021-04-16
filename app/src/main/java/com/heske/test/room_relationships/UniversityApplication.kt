package com.heske.test.room_relationships

import android.app.Application
import com.heske.test.room_relationships.database.Repository
import com.heske.test.room_relationships.database.UniversityDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class UniversityApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()

        val database = UniversityDatabase.getDatabase(this)
        val dataRepository = Repository(
            database.universityDao()
        )

        AppViewModelFactory.inject(
            this,
            dataRepository
        )
    }
}