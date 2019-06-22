package com.example.devapi.database.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "CARS")
data class CarEntity(
        @Id
        @Column(name = "originalUrl", nullable = false, unique = true, length = 1023)
        val originalUrl: String = "",

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "actualizationTime", nullable = false)
        val actualizationTime: Date = Date(),

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "creationTime", nullable = false)
        val creationTime: Date = Date(),

        @Column(name = "price", nullable = false)
        val price: Int = -1,

        @Column(name = "year", nullable = false)
        val year: Int = -1,//TODO don't forget to use -1 as broken value!

        @Column(name = "mileage", nullable = false)
        val mileage: Int = -1,

        @Column(name = "source", nullable = false)
        val source: String = "",

        @Column(name = "city", nullable = false)
        val city: String = "",

        @Column(name = "imageUrl", nullable = false, length = 1023)
        val imageUrl: String = "",

        @Column(name = "tradeMark", nullable = false)
        val tradeMark: String = "",

        @Column(name = "model", nullable = false)
        val model: String = "",

        @Column(name = "color", nullable = false)
        val color: String = "",

        @Column(name = "driveUnit", nullable = false)
        val driveUnit: String = "",

        @Column(name = "bodyType", nullable = false)
        val bodyType: String = "",

        @Column(name = "steeringSide", nullable = false)
        val steeringSide: String = "",

        @Column(name = "comment", nullable = false, length = 20000)
        var comment: String = "",

        @Column(name = "generation", nullable = false)
        val generation: String = "",

        @Column(name = "modification", nullable = false)
        val modification: String = "",

        @Column(name = "condition", nullable = false)
        val condition: String = "",

        @Column(name = "ptsOwners", nullable = false)
        val ptsOwners: String = "",

        @Column(name = "vinNumber", nullable = false)
        val vinNumber: String = "",

        @Column(name = "doorsNumber", nullable = false)
        val doorsNumber: String = "",

        @Column(name = "engineType", nullable = false)
        val engineType: String = "",

        @Column(name = "transmission", nullable = false)
        val transmission: String = "",

        @Column(name = "completion", nullable = false)
        val completion: String = "",

        @Column(name = "watchPlace", nullable = false)
        val watchPlace: String = "",

        @Column(name = "powerSteering", nullable = false, length = 10000)
        val powerSteering: String = "",

        @Column(name = "climateControl", nullable = false, length = 10000)
        val climateControl: String = "",

        @Column(name = "salon", nullable = false, length = 10000)
        val salon: String = "",

        @Column(name = "heating", nullable = false, length = 10000)
        val heating: String = "",

        @Column(name = "windowsPower", nullable = false, length = 10000)
        val windowsPower: String = "",

        @Column(name = "electricDrive", nullable = false, length = 10000)
        val electricDrive: String = "",

        @Column(name = "settingsMemory", nullable = false, length = 10000)
        val settingsMemory: String = "",

        @Column(name = "drivingAssistance", nullable = false, length = 10000)
        val drivingAssistance: String = "",

        @Column(name = "antitheftSystem", nullable = false, length = 10000)
        val antitheftSystem: String = "",

        @Column(name = "airBag", nullable = false, length = 10000)
        val airBag: String = "",

        @Column(name = "activeSafety", nullable = false, length = 10000)
        val activeSafety: String = "",

        @Column(name = "multimediaAndNavigation", nullable = false, length = 10000)
        val multimediaAndNavigation: String = "",

        @Column(name = "audioSystem", nullable = false, length = 10000)
        val audioSystem: String = "",

        @Column(name = "tiresAndWheels", nullable = false, length = 10000)
        val tiresAndWheels: String = "",

        @Column(name = "informationAboutTO", nullable = false, length = 10000)
        val informationAboutTO: String = "",

        @Column(name = "headlights", nullable = false, length = 10000)
        val headlights: String = ""
)