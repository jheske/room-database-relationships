package com.heske.test.room_relationships.database

import androidx.room.*

/**
 * Many-to-many
 * A student can take many classes
 * and a class can have many students in it.
 */
@Entity
data class Course(
    @PrimaryKey(autoGenerate = true)
    val courseId: Long = 0L,
    val name: String = ""
)

/**
 * Relationship between Students and Courses.
 */
@Entity(
    // Composite primaryKey so a Student can't take the same Course twice
    primaryKeys = ["studentId", "courseId"],
    // Enforce valid studentId and courseId
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["studentId"], // in Student table
            childColumns = ["studentId"]   // matches CourseEnrollments.studentId
        ),
        ForeignKey(
            entity = Course::class,
            parentColumns = ["courseId"], // in Course table
            childColumns = ["courseId"]   // matches CourseEnrollments.courseId
        )
    ]
)
data class CourseEnrollment(
    val studentId: Long,
    val courseId: Long,
)

/**
 * Many-to-many Relationships require associatedBy / Junction
 */
data class StudentWithCourses(
    @Embedded
    val student: Student,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "courseId",
        associateBy = Junction(value = CourseEnrollment::class)
    )
    val courses: List<Course>
)

data class CourseWithStudents(
    @Embedded
    val course: Course,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "studentId",
        associateBy = Junction(value = CourseEnrollment::class)
    )
    val students: List<Student>
)


