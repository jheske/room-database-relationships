package com.heske.test.room_relationships.database

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(
    private val universityDao: UniversityDao,
) {


    suspend fun countStudentsWithVehicles(): Int {
        return universityDao.countStudentsWithVehicles()
    }

    /** Insert **/
    suspend fun insertStudent(student: Student): Long {
        val dbId = universityDao.insert(student)
        return dbId
    }

    // A Vehicle has only one owner (ownerId == studentId)
    suspend fun insertVehicle(studentId: Long, vehicle: Vehicle) {
        vehicle.ownerId = studentId
        universityDao.insert(vehicle)
    }

    // A course can have many Students and a Student can have many Courses
    suspend fun insertCourse(course: Course): Long {
        return universityDao.insert(course)
    }

    suspend fun insertCourseEnrollment(studentId: Long,courseId: Long) {
        universityDao.insertCourseEnrollment(CourseEnrollment(studentId,courseId))
    }

    fun insertStudents(list: List<Student>) {
        universityDao.insertStudents(list)
    }

    fun insertVehicles(list: List<Vehicle>) {
        universityDao.insertVehicles(list)
    }

    fun insertCourses(list: List<Course>) {
        universityDao.insertCourses(list)
    }

    /** Select **/
    suspend fun getStudentWithVehicles(): List<StudentWithVehicles> {
        return universityDao.selectStudentsWithVehicles()
    }

    suspend fun getStudentsWithCourses(): List<StudentWithCourses> {
       return universityDao.selectStudentsWithCourses()
    }

    suspend fun getCoursesWithStudents(): List<CourseWithStudents> {
        return universityDao.selectCoursesWithStudents()
    }

    suspend fun clearAllTables(context: Context) {
        withContext(Dispatchers.IO) {
            UniversityDatabase.getDatabase(context).clearAllTables()
        }
    }
}