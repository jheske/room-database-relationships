package com.heske.test.room_relationships.database

import androidx.room.*

/**
 * A one-to-one relationship between a
 * Student and an ApplicationApplicationToUniversity
 * NOTE: The vid calls the class Application, but that's an Android thing, so
 * it's confusing.
 *
 * Similar to one-to-many, except
 * the unique Index enforces that any given studentId
 * can appear in the Application table only once, ie., a student
 * can't have more than 1 ApplicationToUniversity
 */

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["studentId"],
            childColumns = ["studentId"]
        )
    ],
    indices = [Index(value = ["studentId"],unique = true)]
)

data class ApplicationToUniversity(
    @PrimaryKey(autoGenerate = true)
    val applicationId: Long=0L,
    val studentId: Long=0L,
    val applicationText: String=""
)

data class StudentWithApplicationToUniversity(
    @Embedded
    val student: Student,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "studentId"
    )
    // Only one allowed, so this is NOT a List like in the one-to-many example
    // This is non-nullable, so make sure there is one!
    val applicationToUniversity: ApplicationToUniversity
)
