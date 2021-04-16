package com.heske.test.room_relationships.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Student::class, Vehicle::class, ApplicationToUniversity::class, Course::class, CourseEnrollment::class],
    version = 1,
    exportSchema = false
)
abstract class UniversityDatabase : RoomDatabase() {
    abstract fun universityDao(): UniversityDao

    companion object {

        @Volatile
        private var INSTANCE: UniversityDatabase? = null

        fun getDatabase(
            context: Context
        ): UniversityDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UniversityDatabase::class.java,
                    "university_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    .fallbackToDestructiveMigration()
                        // TODO add this to pre-populate database
//                    .addCallback(object : Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
//                            val students = MockData.students
//                            Executors.newSingleThreadScheduledExecutor().execute {
//                                getDatabase(context).universityDao()
//                                    .insertStudents(MockData.students)
//                            }
//                        }
//                    })
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}