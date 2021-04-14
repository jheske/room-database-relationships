package com.heske.test.room_relationships

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

/**
 * NOTE: All relationship queries require
 * a @Transaction because behind the scenes
 * Room has to perform multiple queries.
 */

@Dao
interface UniversityDao {
    // ONE-TO-MANY
    // Return a list of Students, each having many Vehicles
    // Return all StudentWithVehicles, ie., all Students with their Vehicles
    // Transaction b/c Room has to run TWO SQL statements behind the scenes:
    // one to get every Student, and another to get every Vehicle that belongs to
    // the Student
    @Query("SELECT * from Student")
    @Transaction
    fun fetchStudentsWithVehicles(): LiveData<List<StudentWithVehicles>>

    // ONE-TO-ONE
    // Return a list of Students, each having one ApplicationToUniversity
    // NOTE ApplicationToUniversity is non-nullable, so if a Student doesn't have an associated
    // ApplicationToUniversity, the query will fail. Several solutions to this:
    //   1. Make sure there's an ApplicationToUniversity for every Student
    //   2. Make the ApplicationToUniversity reference NULLABLE
    //   3. Only query for Students who DO have an associated ApplicationToUniversity
    @Query("SELECT * from Student")
    @Transaction
    fun fetchStudentsWithApplicationToUniversity(): LiveData<List<StudentWithApplicationToUniversity>>

    // MANY-TO-MANY
    @Query("SELECT * from Student")
    @Transaction
    fun fetchStudentsWithClasses(): LiveData<List<StudentWithCourses>>

    // MANY-TO-MANY
    @Query("SELECT * from Course")
    @Transaction
    fun fetchCoursesWithStudents(): LiveData<List<CourseWithStudents>>

    @Insert
    suspend fun insert(student: Student): Long

    @Insert
    suspend fun insert(course: Course): Long

    @Insert
    suspend fun insert(vehicle: Vehicle): Long
}