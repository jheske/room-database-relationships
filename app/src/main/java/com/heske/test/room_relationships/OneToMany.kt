package com.heske.test.room_relationships

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * FOR THE GORY DETAILS...
 * Android will generate a UniversityDao_Impl class containing
 * all the low-level SQLite code (cursors, loaders, behind-the-scenes additional queries,
 * address_ embedded fields, etc).
 */

// A Student can have multiple Vehicles, but a Vehicle
// belongs to only one Student
// @Embedded is a simple way to tell Room to fetch an Object and
// append all its fields to the resulting Student results
// a prefix isn't necessary but helpful to see in the results.
@Entity
data class Student(
    @PrimaryKey(autoGenerate = true)
    val studentId: Long = 0L,
    val firstName: String = "",
    val lastName: String = "",
    @Embedded(prefix="address_")
    val address: Address
)

data class Address(
    val streetName: String="",
    val streetNumber: String =""
)

// Define Vehicle Entity such that
// Vehicle.ownerId must be a valid Student.studentId
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["studentId"],
            childColumns = ["ownerId"]
        )
    ]
)
data class Vehicle(
    @PrimaryKey
    val vehicleId: Long = 0L,
    val vehicleType: String = "",
    val ownerId: Long=0L
)

data class StudentWithVehicles(
    // Embedded so Room will map each column from Student into the resulting StudentWithVehicles
    @Embedded
    val student: Student,
    // This is the Relationship defined in the Foreign key above. Enforces that
    // ownerId must be a valid studentId
    @Relation(
        parentColumn = "studentId",  // in Student table
        entityColumn = "ownerId"     // matches Vehicle table
    )
    val vehicles: List<Vehicle>
)


