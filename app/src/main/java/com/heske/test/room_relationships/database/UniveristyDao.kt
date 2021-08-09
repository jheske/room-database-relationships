package com.heske.test.room_relationships.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * NOTE: All relationship queries require
 * a @Transaction because behind the scenes
 * Room has to perform multiple queries.
 */

@Dao
interface UniversityDao {
    /***** ONE-TO-ONE *****/
    // Return a list of Students, each having one ApplicationToUniversity
    // NOTE ApplicationToUniversity is non-nullable, so if a Student doesn't have an associated
    // ApplicationToUniversity, the query will fail. Several solutions to this:
    //   1. Make sure there's an ApplicationToUniversity for every Student
    //   2. Make the ApplicationToUniversity reference NULLABLE
    //   3. Only query for Students who DO have an associated ApplicationToUniversity
    @Query("SELECT * from Student")
    @Transaction
    fun selectStudentsWithApplicationToUniversity(): LiveData<List<StudentWithApplicationToUniversity>>

    /***** ONE-TO-MANY ****/
    // Return a list of Students, each having many Vehicles
    // Return all StudentWithVehicles, ie., all Students with their Vehicles
    // Transaction b/c Room has to run TWO SQL statements behind the scenes:
    // one to get every Student, and another to get every Vehicle that belongs to
    // the Student
    @Query("SELECT * from Student")
    @Transaction
    suspend fun selectStudentsWithVehicles(): List<StudentWithVehicles>

    /***** MANY-TO-MANY *****/
    @Query("SELECT * from Student")
    @Transaction
    suspend fun selectStudentsWithCourses(): List<StudentWithCourses>

    @Query("SELECT * from Course")
    @Transaction
    suspend fun selectCoursesWithStudents(): List<CourseWithStudents>

    /** Insert **/

    @Insert
    suspend fun insert(student: Student): Long

    @Insert
    suspend fun insert(course: Course): Long

    @Insert
    suspend fun insert(vehicle: Vehicle): Long

    @Insert
    suspend fun insertCourseEnrollment(courseEnrollment: CourseEnrollment): Long

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertStudents(list: List<Student>)

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertVehicles(list: List<Vehicle>)

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertCourses(list: List<Course>)


    /** Insert Many to Many **/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: CourseWithStudents)

    /** Other Useful queries **/
    @Query("SELECT count(*) from Student")
    suspend fun countStudentsWithVehicles(): Int
}