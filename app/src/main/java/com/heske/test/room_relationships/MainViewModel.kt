package com.heske.test.room_relationships

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.heske.test.room_relationships.database.*
import kotlinx.coroutines.launch

class MainViewModel(dataRepository: Repository) : AppViewModel(dataRepository) {

    var student1Id = 0L
    var student2Id = 0L
    var student3Id = 0L
    var course1Id = 0L
    var course2Id = 0L
    var course3Id = 0L

    private val _studentsWithVehicles = MutableLiveData<List<StudentWithVehicles>>()
    val studentsWithVehicles: LiveData<List<StudentWithVehicles>> = _studentsWithVehicles

    private val _studentsWithCourses = MutableLiveData<List<StudentWithCourses>>()
    val studentsWithCourses: LiveData<List<StudentWithCourses>> = _studentsWithCourses

    private val _coursesWithStudents = MutableLiveData<List<CourseWithStudents>>()
    val coursesWithStudents: LiveData<List<CourseWithStudents>> = _coursesWithStudents

    // TODO add database .callback to prepopulate data
    fun addData(context: Context) {
        viewModelScope.launch {
            dataRepository.clearAllTables(context)
            // Students
            student1Id = dataRepository.insertStudent(MockData.student1)
            student2Id = dataRepository.insertStudent(MockData.student2)
            student3Id = dataRepository.insertStudent(MockData.student3)
            // Vehicles
            dataRepository.insertVehicle(student1Id, MockData.vehicle1)
            dataRepository.insertVehicle(student1Id, MockData.vehicle2)
            dataRepository.insertVehicle(student2Id, MockData.vehicle3)
            dataRepository.insertVehicle(student2Id, MockData.vehicle4)
            dataRepository.insertVehicle(student3Id, MockData.vehicle5)
            dataRepository.insertVehicle(student3Id, MockData.vehicle6)
            getStudentsWithVehicles()
            // Courses
            course1Id = dataRepository.insertCourse(MockData.course1)
            course2Id = dataRepository.insertCourse(MockData.course2)
            course3Id = dataRepository.insertCourse(MockData.course3)
            // CourseEnrollments
            dataRepository.insertCourseEnrollment(student1Id,course1Id)
            dataRepository.insertCourseEnrollment(student1Id,course2Id)
            dataRepository.insertCourseEnrollment(student1Id,course3Id)
            getStudentsWithCourses()
            getCoursesWithStudents()
        }
    }

    private fun getStudentsWithVehicles() {
        viewModelScope.launch {
            _studentsWithVehicles.value = dataRepository.getStudentWithVehicles()
        }
    }

    private fun getStudentsWithCourses() {
        viewModelScope.launch {
            _studentsWithCourses.value = dataRepository.getStudentsWithCourses()
        }
    }

    private fun getCoursesWithStudents() {
        viewModelScope.launch {
            _coursesWithStudents.value = dataRepository.getCoursesWithStudents()
        }
    }
}