package com.heske.test.room_relationships.database

import java.util.*
import java.util.concurrent.TimeUnit

class MockData {
    companion object {
        fun generateVehiclesForStudents(
            students: List<Student>
        ): List<Vehicle> {
            val comments: MutableList<Vehicle> = ArrayList<Vehicle>()
            val rnd = Random()
            for (student in students) {
                val vehicleNumber = rnd.nextInt(5) + 1
                for (i in 0 until vehicleNumber) {
                    val vehicle = Vehicle()
                    vehicle.ownerId = student.studentId
                    vehicle.vehicleType ="vehicle #$vehicleNumber"
                    comments.add(vehicle)
                }
            }
            return comments
        }
        
        val student1 = Student(
            firstName = "Jill",
            lastName = "Heske",
            address = Address(
                streetNumber = "10",
                streetName = "Main Street"
            )
        )
        val student2 = Student(
            firstName = "Jill",
            lastName = "Heske",
            address = Address(
                streetNumber = "10",
                streetName = "Main Street"
            )
        )
        val student3 = Student(
            firstName = "Jill",
            lastName = "Heske",
            address = Address(
                streetNumber = "10",
                streetName = "Main Street"
            )
        )

        val vehicle1 = Vehicle(
            vehicleType = "Tesla"
        )
        val vehicle2 = Vehicle(
            vehicleType = "Chevy Sedan"
        )
        val vehicle3 = Vehicle(
            vehicleType = "Mercedes S100"
        )
        val vehicle4 = Vehicle(
            vehicleType = "Ford Pickup"
        )
        val vehicle5 = Vehicle(
            vehicleType = "Honda CRV"
        )
        val vehicle6 = Vehicle(
            vehicleType = "1978 Corvette"
        )

        val course1 = Course(
            name="Math"
        )
        val course2 = Course(
            name="English"
        )
        val course3 = Course(
            name="Science"
        )
    }
}