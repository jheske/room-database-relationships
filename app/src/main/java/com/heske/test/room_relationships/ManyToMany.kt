package com.heske.test.room_relationships

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
 * Relationship between the two classes.
 */
@Entity(
    // Composite primaryKey so a Student can't take the same Course twice
    primaryKeys = ["studentId", "courseId"],
    // Enforce valid studentId and courseId
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["studentId"], // in Student table
            childColumns = ["studentId"]   // matches CourseEnrollments
        ),
        ForeignKey(
            entity = Course::class,
            parentColumns = ["courseId"], // in Course table
            childColumns = ["courseId"]   // matches CourseEnrollments
        )
    ]
)
data class CourseEnrollments(
    val studentId: Long = 0L,
    val courseId: Long = 0L

)

/**
 * Many-to-many requires associatedBy
 */
data class StudentWithCourses(
    @Embedded
    val student: Student,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "courseId",
        associateBy = Junction(value = CourseEnrollments::class)
    )
    val courses: List<Course>
)

data class CourseWithStudents(
    @Embedded
    val course: Course,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "studentIt",
        associateBy = Junction(value = CourseEnrollments::class)
    )
    val students: List<Student>
)

